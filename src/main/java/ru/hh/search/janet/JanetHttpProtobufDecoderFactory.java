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

public class JanetHttpProtobufDecoderFactory {

  private Map<String, Map<String, Message>> prototypes;

  public JanetHttpProtobufDecoderFactory() {
    prototypes = new ConcurrentHashMap<String, Map<String, Message>>();
  }

  synchronized public void unregisterService(Service service) {
    this.prototypes.remove( service.getDescriptorForType().getFullName() );
  }

  synchronized public void registerService(Service service) {

    String serviceName = service.getDescriptorForType().getFullName();
    Map<String, Message> methods = new ConcurrentHashMap<String, Message>();
    
    for (MethodDescriptor md : service.getDescriptorForType().getMethods()){
      methods.put(md.getFullName(), service.getRequestPrototype(md));
    }
    this.prototypes.put(serviceName, methods);

  }

  public JanetHttpProtobufDecoder getDecoder(){
    return new JanetHttpProtobufDecoder(prototypes);
  }
}
