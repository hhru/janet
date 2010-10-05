/*
 * Copyright (c) 2009 Stephen Tu <stephen_tu@berkeley.edu>
 * Copyright (c) 2010 Pavel Trukhanov <p.trukhanov@hh.ru>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 */

package ru.hh.search.janet;

import org.jboss.netty.buffer.BigEndianHeapChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.http.DefaultHttpResponse;
import org.jboss.netty.handler.codec.http.HttpResponse;
import org.jboss.netty.handler.codec.http.HttpResponseStatus;
import org.jboss.netty.handler.codec.http.HttpVersion;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.hh.search.janet.exception.InvalidResponseException;

public class JanetHttpProtobufEncoder extends OneToOneEncoder{

  Logger logger = LoggerFactory.getLogger(JanetHttpProtobufEncoder.class);


  protected Object encode(ChannelHandlerContext ctx, Channel channel, Object msg) throws Exception {

    JanetRpcResponse jResponse = null;
    try {
      jResponse = (JanetRpcResponse) msg;
    } catch (Exception e) {
      throw new InvalidResponseException(e, null, "Cannot decode RpcResponse.");
    }

    HttpResponse response;

    if (jResponse != null && jResponse.errCode == null){

      logger.info("Http Response Status: "+HttpResponseStatus.OK.toString());
      logger.info("Http Version: "+jResponse.version.toString());
      response = new DefaultHttpResponse(jResponse.version, HttpResponseStatus.OK);

      logger.info("Content-Type: text/x-protobuf");
      response.setHeader("Content-Type", "text/x-protobuf");

      logger.info("Http Response Body (not serialized in proto): " + jResponse.responseMessage.toString());
      response.setContent(new BigEndianHeapChannelBuffer(jResponse.responseMessage.toByteArray()));
    }
    else {

      HttpVersion version = (jResponse != null) ? jResponse.version : HttpVersion.HTTP_1_0;

      logger.info("Http Response Status: "+HttpResponseStatus.INTERNAL_SERVER_ERROR.toString());
      logger.info("Http Version: "+version.toString());
      response = new DefaultHttpResponse(version, HttpResponseStatus.INTERNAL_SERVER_ERROR);

      logger.info("Content-Type: text/plain");
      response.setHeader("Content-Type", "text/plain");

      logger.info("Http Response Body: " + jResponse.errCode.name()+":"+jResponse.errorMessage);
      response.setContent(new BigEndianHeapChannelBuffer((jResponse.errCode.name()+":"+jResponse.errorMessage).getBytes()));
    }
    return response;

  }
}
