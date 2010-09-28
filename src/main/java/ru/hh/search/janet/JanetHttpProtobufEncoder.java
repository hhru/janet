/*
 * Copyright (c) 2009 Stephen Tu <stephen_tu@berkeley.edu>
 * Copyright (c) 2010 Pavel Trukhanov <p.trukhanov@hh.ru>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 */

package ru.hh.search.janet;

import org.jboss.netty.buffer.BigEndianHeapChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.http.DefaultHttpResponse;
import org.jboss.netty.handler.codec.http.HttpResponse;
import org.jboss.netty.handler.codec.http.HttpResponseStatus;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;

public class JanetHttpProtobufEncoder extends OneToOneEncoder{

  protected Object encode(ChannelHandlerContext ctx, Channel channel, Object msg) throws Exception {

    if (!(msg instanceof ChannelBuffer)) {
      return msg;
    }

    JanetRpcResponse jResponse = (JanetRpcResponse) msg;

    HttpResponse response = new DefaultHttpResponse(jResponse.version, HttpResponseStatus.OK);

    response.addHeader("Content-Type", "text/x-protobuf");
    response.setContent(new BigEndianHeapChannelBuffer(jResponse.responseMessage.toByteArray()));

    return response;

  }
}
