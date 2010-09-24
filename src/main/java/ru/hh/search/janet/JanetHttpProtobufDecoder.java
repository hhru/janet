/*
 * Copyright (c) 2009 Stephen Tu <stephen_tu@berkeley.edu>
 * Copyright (c) 2010 Pavel Trukhanov <p.trukhanov@hh.ru>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 */
package ru.hh.search.janet;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import java.util.Map;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBufferInputStream;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.http.DefaultHttpRequest;
import org.jboss.netty.handler.codec.oneone.OneToOneDecoder;
import ru.hh.search.janet.exception.InvalidJanetRpcRequestException;

public class JanetHttpProtobufDecoder extends OneToOneDecoder {

  private Map<String, Map<String, Message>> prototypes;
//  private ExtensionRegistry extensionRegistry;

  public JanetHttpProtobufDecoder(Map<String, Map<String, Message>> prototypes) {
    this.prototypes = prototypes;
  }

  /*public JanetHttpProtobufDecoder(Map<String, Message> prototypes, ExtensionRegistry extensionRegistry) {
    this.prototypes = prototypes;
    this.extensionRegistry = extensionRegistry;
  }*/


  protected Object decode(ChannelHandlerContext ctx, Channel channel, Object msg) throws Exception {
    if (!(msg instanceof ChannelBuffer)) {
      return msg;
    }

    DefaultHttpRequest request = (DefaultHttpRequest) msg;
    String[] uriParsed = uriParse(request.getUri());
    String serviceName = uriParsed[0];
    String methodName = uriParsed[1];
    Message prototype = prototypes.get(serviceName).get(methodName);

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
      message = prototype.newBuilderForType().mergeFrom(
          new ChannelBufferInputStream((ChannelBuffer) msg)
      ).build();
    }catch (InvalidProtocolBufferException ex) {
      throw new InvalidJanetRpcRequestException(ex, "Could not build method request message");
    }
    return new JanetRpcRequest(serviceName, methodName, message);
  }

  private static String[] uriParse(String uri){
    return uri.split("/");
  }

}
