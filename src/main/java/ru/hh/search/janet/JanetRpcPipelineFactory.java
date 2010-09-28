/*
 * Copyright (c) 2009 Stephen Tu <stephen_tu@berkeley.edu>
 * Copyright (c) 2010 Pavel Trukhanov <p.trukhanov@hh.ru>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 */

package ru.hh.search.janet;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.handler.codec.http.HttpChunkAggregator;
import org.jboss.netty.handler.codec.http.HttpRequestDecoder;
import org.jboss.netty.handler.codec.http.HttpResponseEncoder;
import org.jboss.netty.handler.stream.ChunkedWriteHandler;

class JanetRpcPipelineFactory implements ChannelPipelineFactory {

	private final ChannelUpstreamHandlerFactory handlerFactory;
  private final JanetHttpProtobufDecoderFactory decoderFactory;

  JanetRpcPipelineFactory(ChannelUpstreamHandlerFactory handlerFactory, JanetHttpProtobufDecoderFactory decoderFactory) {
    this.handlerFactory = handlerFactory;
    this.decoderFactory = decoderFactory;
  }

  public ChannelPipeline getPipeline() throws Exception {
		ChannelPipeline p = Channels.pipeline();

    p.addLast("decoder", new HttpRequestDecoder());
    p.addLast("aggregator", new HttpChunkAggregator(65536));
    p.addLast("protobufDecoder", decoderFactory.getDecoder());

    p.addLast("protobufEncoder", new JanetHttpProtobufEncoder());
    p.addLast("encoder", new HttpResponseEncoder());
    p.addLast("chunkedWriter", new ChunkedWriteHandler());

		p.addLast("handler", handlerFactory.getChannelUpstreamHandler()); 
		return p;
	}

}
