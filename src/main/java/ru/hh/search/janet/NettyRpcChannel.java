/*
 * Copyright (c) 2009 Stephen Tu <stephen_tu@berkeley.edu>
 * Copyright (c) 2010 Pavel Trukhanov <p.trukhanov@hh.ru>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 */

package ru.hh.search.janet;

import com.google.protobuf.Descriptors.MethodDescriptor;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.RpcCallback;
import com.google.protobuf.RpcChannel;
import com.google.protobuf.RpcController;
import org.apache.log4j.Logger;
import org.jboss.netty.channel.Channel;
import ru.hh.search.janet.NettyRpcProto.RpcRequest;
import ru.hh.search.janet.NettyRpcProto.RpcRequest.Builder;
import ru.hh.search.janet.NettyRpcProto.RpcResponse;

public class NettyRpcChannel implements RpcChannel {

	private static final Logger logger = Logger.getLogger(NettyRpcChannel.class);
	
	private final Channel channel;
	private final NettyRpcClientChannelUpstreamHandler handler;
	
	public NettyRpcChannel(Channel channel) {
		this.channel = channel;
		this.handler = channel.getPipeline().get(NettyRpcClientChannelUpstreamHandler.class);
		if (handler == null) {
			throw new IllegalArgumentException("Channel does not have proper handler");
		}
	}
	
	public RpcController newRpcController() {
		return new JanetRpcController();
	}
	
	public void callMethod(MethodDescriptor method, RpcController controller,
			Message request, Message responsePrototype, RpcCallback<Message> done) {
		int nextSeqId = (done == null) ? -1 : handler.getNextSeqId();
		Message rpcRequest = buildRequest(done != null, nextSeqId, false, method, request);
		if (done != null) {
			handler.registerCallback(nextSeqId, new ResponsePrototypeRpcCallback(controller, responsePrototype, done));
		}
		channel.write(rpcRequest);
	}


	public void close() {
		channel.close().awaitUninterruptibly();
	}

	private Message buildRequest(boolean hasSequence, int seqId, boolean isBlocking, MethodDescriptor method, Message request) {
		Builder requestBuilder = RpcRequest.newBuilder();
		if (hasSequence) {
			requestBuilder.setId(seqId);
		}
		return requestBuilder
			.setIsBlockingService(isBlocking)
			.setServiceName(method.getService().getFullName())
			.setMethodName(method.getName())
			.setRequestMessage(request.toByteString())
			.build();
	}
	
	static class ResponsePrototypeRpcCallback implements RpcCallback<RpcResponse> {
		
		private final RpcController controller;
		private final Message responsePrototype;
		private final RpcCallback<Message> callback; 
		
		private RpcResponse rpcResponse;
		
		public ResponsePrototypeRpcCallback(RpcController controller, Message responsePrototype, RpcCallback<Message> callback) {
			if (responsePrototype == null) {
				throw new IllegalArgumentException("Must provide response prototype");
			} else if (callback == null) {
				throw new IllegalArgumentException("Must provide callback");
			}
			this.controller = controller;
			this.responsePrototype = responsePrototype;
			this.callback = callback;
		}
		
		public void run(RpcResponse message) {
			rpcResponse = message;
			try {
				Message response = (message == null || !message.hasResponseMessage()) ? 
						null : 
						responsePrototype.newBuilderForType().mergeFrom(message.getResponseMessage()).build();
				callback.run(response);
			} catch (InvalidProtocolBufferException e) {
				logger.warn("Could not marshall into response", e);
				if (controller != null) {
					controller.setFailed("Received invalid response type from server");
				}
				callback.run(null);
			}
		}
		
		public RpcController getRpcController() {
			return controller;
		}
		
		public RpcResponse getRpcResponse() {
			return rpcResponse;
		}
		
	}
	
}
