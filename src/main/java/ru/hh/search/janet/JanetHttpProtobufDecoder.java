/*
 * Copyright (c) 2009 Stephen Tu <stephen_tu@berkeley.edu>
 * Copyright (c) 2010 Pavel Trukhanov <p.trukhanov@hh.ru>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 */
package ru.hh.search.janet;

import com.google.protobuf.Message;
import java.util.Map;
import org.jboss.netty.buffer.ChannelBufferInputStream;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.http.DefaultHttpRequest;
import org.jboss.netty.handler.codec.oneone.OneToOneDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.hh.search.janet.exception.InvalidRequestException;
import ru.hh.search.janet.exception.NoSuchServiceException;
import ru.hh.search.janet.exception.NoSuchServiceMethodException;

public class JanetHttpProtobufDecoder extends OneToOneDecoder {

  private final Map<String, Map<String, Message>> prototypes;
//  private ExtensionRegistry extensionRegistry;

  Logger logger = LoggerFactory.getLogger(JanetHttpProtobufDecoder.class);

  public JanetHttpProtobufDecoder(Map<String, Map<String, Message>> prototypes) {
    super();
    this.prototypes = prototypes;
  }

  /*public JanetHttpProtobufDecoder(Map<String, Message> prototypes, ExtensionRegistry extensionRegistry) {
    this.prototypes = prototypes;
    this.extensionRegistry = extensionRegistry;
  }*/


  protected Object decode(ChannelHandlerContext ctx, Channel channel, Object msg) throws Exception {

    DefaultHttpRequest request = null;
    try {
      request = (DefaultHttpRequest) msg;
    } catch (Exception e) {
      throw new InvalidRequestException(e, null, "Cannot decode HttpRequest.");
    }
    logger.info("Request uri: " + request.getUri());

    String serviceName = null;
    String methodName = null;
    String[] uriParsed = uriParse(request.getUri());
    try {
      serviceName = uriParsed[1];
    } catch (NullPointerException e) {
      throw new NoSuchServiceException(e, "No serviceName in uri: "+request.getUri());
    }

    try {
      methodName = uriParsed[2];
    } catch (NullPointerException e) {
      throw new NoSuchServiceMethodException(e, "No methodName in uri: "+request.getUri());
    }
    logger.info("Received request for serviceName: " + serviceName + ", method: " + methodName);


    Message prototype;
    Map<String, Message> method2prototype = null;
    try {
      method2prototype = prototypes.get(serviceName);
    } catch (Exception e) {
      throw new NoSuchServiceException(e, serviceName);
    }

    try {
      prototype = method2prototype.get(methodName);
    } catch (Exception e) {
      throw new NoSuchServiceMethodException(e, methodName);
    }

    if (prototype == null)
    {
      throw new InvalidRequestException(new Exception(), "No response prototype found.");
    }

    /*if (extensionRegistry == null) {
      return new JanetRpcRequest(uri, prototype.newBuilderForType().mergeFrom(
          new ChannelBufferInputStream((ChannelBuffer) msg)).build());
    } else {
      return new JanetRpcRequest(uri, prototype.newBuilderForType().mergeFrom(
          new ChannelBufferInputStream((ChannelBuffer) msg),
          extensionRegistry).build());
    }*/

    Message message;
    try {
      ChannelBufferInputStream bodyStream = new ChannelBufferInputStream(request.getContent());
      message = prototype.newBuilderForType().mergeFrom(bodyStream).build();
    }catch (Exception ex) {
      throw new InvalidRequestException(ex, "Could not build messagefrom request.");
    }
    
    return new JanetRpcRequest(serviceName, methodName, message, request.getProtocolVersion());
  }

  private static String[] uriParse(String uri){
    return uri.split("/");
  }

}
