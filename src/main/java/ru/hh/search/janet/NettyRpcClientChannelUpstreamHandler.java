/*
 * Copyright (c) 2009 Stephen Tu <stephen_tu@berkeley.edu>
 * Copyright (c) 2010 Pavel Trukhanov <p.trukhanov@hh.ru>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 */

package ru.hh.search.janet;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.Logger;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelPipelineCoverage;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

import ru.hh.search.janet.NettyRpcChannel.ResponsePrototypeRpcCallback;
import ru.hh.search.janet.NettyRpcProto.RpcResponse;

@ChannelPipelineCoverage("one")
public class NettyRpcClientChannelUpstreamHandler extends SimpleChannelUpstreamHandler {

	private static final Logger logger = Logger.getLogger(NettyRpcClientChannelUpstreamHandler.class);
	
	private final AtomicInteger seqNum = new AtomicInteger(0);
	
	private final Map<Integer, ResponsePrototypeRpcCallback> callbackMap = new ConcurrentHashMap<Integer, ResponsePrototypeRpcCallback>();
	
	public int getNextSeqId() {
		return seqNum.getAndIncrement();
	}
	
	public synchronized void registerCallback(int seqId, ResponsePrototypeRpcCallback callback) {
		if (callbackMap.containsKey(seqId)) {
			throw new IllegalArgumentException("Callback already registered");
		}
		callbackMap.put(seqId, callback);
	}
	
	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) {
		logger.info("Channel connected");
	}

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
		
		RpcResponse response = (RpcResponse) e.getMessage();
		
		if (!response.hasId()) {
			logger.debug("Should never receive response without seqId");
			return;
		}
		
		int seqId = response.getId();
		ResponsePrototypeRpcCallback callback = callbackMap.remove(seqId);
		
		if (response.hasErrorCode() && callback != null && callback.getRpcController() != null) {
			callback.getRpcController().setFailed(response.getErrorMessage());
		}
		
		if (callback == null) {
			logger.debug("Received response with no callback registered");
		} else {
			logger.debug("Invoking callback with response");
			callback.run(response);
		}
		
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
		logger.error("Unhandled exception in handler", e.getCause());
		e.getChannel().close();
		throw new Exception(e.getCause());
	}
	
}
