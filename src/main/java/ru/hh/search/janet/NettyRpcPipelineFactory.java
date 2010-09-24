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
import org.jboss.netty.handler.codec.frame.LengthFieldBasedFrameDecoder;
import org.jboss.netty.handler.codec.frame.LengthFieldPrepender;
import org.jboss.netty.handler.codec.protobuf.ProtobufDecoder;
import org.jboss.netty.handler.codec.protobuf.ProtobufEncoder;

import com.google.protobuf.Message;

class NettyRpcPipelineFactory implements ChannelPipelineFactory {
	
	private final ChannelUpstreamHandlerFactory handlerFactory;
	private final Message defaultInstance;
	
	NettyRpcPipelineFactory(ChannelUpstreamHandlerFactory handlerFactory, Message defaultInstance) {
		this.handlerFactory = handlerFactory;
		this.defaultInstance = defaultInstance;
	}
	
	public ChannelPipeline getPipeline() throws Exception {
		ChannelPipeline p = Channels.pipeline();
		p.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(MAX_FRAME_BYTES_LENGTH, 0, 4, 0, 4));
		p.addLast("protobufDecoder", new ProtobufDecoder(defaultInstance));
		  
		p.addLast("frameEncoder", new LengthFieldPrepender(4));
		p.addLast("protobufEncoder", new ProtobufEncoder());
		
		p.addLast("handler", handlerFactory.getChannelUpstreamHandler()); 
		return p;
	}
	
	private static final int MAX_FRAME_BYTES_LENGTH = 1048576;
}
