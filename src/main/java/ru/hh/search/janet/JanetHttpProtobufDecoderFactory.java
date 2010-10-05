/*
 * Copyright (c) 2009 Stephen Tu <stephen_tu@berkeley.edu>
 * Copyright (c) 2010 Pavel Trukhanov <p.trukhanov@hh.ru>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 */
package ru.hh.search.janet;

import com.google.protobuf.Descriptors.MethodDescriptor;
import com.google.protobuf.Message;
import com.google.protobuf.Service;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class  JanetHttpProtobufDecoderFactory {

  Logger logger = LoggerFactory.getLogger(JanetHttpProtobufDecoderFactory.class);

  private Map<String, Map<String, Message>> prototypes;

  public JanetHttpProtobufDecoderFactory() {
    prototypes = new ConcurrentHashMap<String, Map<String, Message>>();
  }

  synchronized public void unregisterService(Service service) {
    this.prototypes.remove( service.getDescriptorForType().getFullName() );
  }

  synchronized public void registerService(Service service) {

    logger.info("Registring decoder prototypes");

    //String serviceName = service.getDescriptorForType().getFullName();
    String serviceName = service.getDescriptorForType().getName();

    logger.info("Service: "+serviceName);

    Map<String, Message> methods = new ConcurrentHashMap<String, Message>();
    
    for (MethodDescriptor md : service.getDescriptorForType().getMethods()){
      String methodName = md.getName();
      Message prototype = service.getRequestPrototype(md);

      logger.info("Method \""+methodName + "\" prototype: " + prototype.getDescriptorForType().getName());
      
      methods.put(methodName, prototype);
    }
    this.prototypes.put(serviceName, methods);

  }

  public JanetHttpProtobufDecoder getDecoder(){
    logger.info("Creating decoder.");
    return new JanetHttpProtobufDecoder(prototypes);
  }
}
