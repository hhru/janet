/*
 * Copyright (c) 2009 Stephen Tu <stephen_tu@berkeley.edu>
 * Copyright (c) 2010 Pavel Trukhanov <p.trukhanov@hh.ru>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 */
package ru.hh.search.janet;

import com.google.protobuf.Descriptors.MethodDescriptor;
import com.google.protobuf.Message;
import com.google.protobuf.RpcCallback;
import com.google.protobuf.RpcController;
import com.google.protobuf.Service;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFutureListener;
import org.jboss.netty.channel.ChannelHandler.Sharable;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.jboss.netty.handler.codec.http.HttpVersion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.hh.search.janet.JanetRpcResponse.ErrorCode;
import ru.hh.search.janet.exception.InvalidRequestException;
import ru.hh.search.janet.exception.InvalidResponseException;
import ru.hh.search.janet.exception.JanetRpcException;
import ru.hh.search.janet.exception.NoSuchServiceException;
import ru.hh.search.janet.exception.NoSuchServiceMethodException;
import ru.hh.search.janet.exception.RpcServiceException;

@Sharable
public class JanetRpcServerChannelUpstreamHandler extends SimpleChannelUpstreamHandler {

  private static final Logger logger = LoggerFactory.getLogger(JanetRpcServerChannelUpstreamHandler.class);

  private final Map<String, Service> serviceMap = new ConcurrentHashMap<String, Service>();

  public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {

    logger.info(JanetRpcServerChannelUpstreamHandler.class.getName());

    final JanetRpcRequest request = (JanetRpcRequest) e.getMessage();

    logger.info("Received request for serviceName: " + request.serviceName + ", method: " + request.methodName);

    Service service = serviceMap.get(request.serviceName);
    if (service == null) {
      throw new NoSuchServiceException(request);
    } else {
      MethodDescriptor methodDescriptor = service.getDescriptorForType().findMethodByName(request.methodName);

      if (methodDescriptor == null) {
        throw new NoSuchServiceMethodException(request);
      } else {

        final Channel channel = e.getChannel();
        final RpcController controller = new JanetRpcController();

        RpcCallback<Message> callback = new RpcCallback<Message>() {
          public void run(Message methodResponse) {
            JanetRpcResponse responce = new JanetRpcResponse(request.requestMessage, "This could not happen.", ErrorCode.RPC_FAILED, request.version);
            if (methodResponse != null) {
              responce = new JanetRpcResponse(methodResponse, request.version);
            } else {
              logger.info("service callback returned null message");
              if (controller.errorText() != null) {
                responce.setErrorMessage(controller.errorText());
              }
              else {
                responce.setErrorMessage("service callback returned null message");
              }
            }
            channel.write(responce).addListener(ChannelFutureListener.CLOSE);
          }
        };

        try {
          service.callMethod(methodDescriptor, controller, request.requestMessage, callback);
        } catch (Exception ex) {
          throw new JanetRpcException(ex, request, "Service threw unexpected exception");
        }

      }
    }

  }


  public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {

    logger.warn("exceptionCaught", e.getCause());

		ErrorCode errorCode;

		if (e.getCause() instanceof NoSuchServiceException) {
			errorCode = ErrorCode.SERVICE_NOT_FOUND;
		} else if (e.getCause() instanceof NoSuchServiceMethodException) {
			errorCode =ErrorCode.METHOD_NOT_FOUND;
		} else if (e.getCause() instanceof InvalidRequestException) {
			errorCode = ErrorCode.BAD_REQUEST_PROTO;
    } else if (e.getCause() instanceof InvalidResponseException) {
			errorCode = ErrorCode.BAD_RESPONSE_PROTO;
		} else if (e.getCause() instanceof RpcServiceException) {
			errorCode = ErrorCode.RPC_ERROR;
		} else if (e.getCause() instanceof JanetRpcException) {
			errorCode = ErrorCode.RPC_FAILED;
		} else {
			/* Cannot respond to this exception, because it is not tied
			 * to a request */
			logger.info("Cannot respond to handler exception", e.getCause());
			return;
		}

		JanetRpcException ex = (JanetRpcException) e.getCause();

    JanetRpcResponse response = null;

		if (ex.getRpcRequest() != null ) {
			response = new JanetRpcResponse(ex.getRpcRequest().requestMessage, ex.getMessage(), errorCode, ex.getRpcRequest().version);
		} else {
      response = new JanetRpcResponse(null, ex.getMessage(), errorCode, HttpVersion.HTTP_1_0);
      logger.info("Cannot respond to handler exception", ex);
		}

    e.getChannel().write(response).addListener(ChannelFutureListener.CLOSE);
  }

  synchronized void registerService(Service service) {
    //String serviceName = service.getDescriptorForType().getFullName();
    String serviceName = service.getDescriptorForType().getName();

    if(serviceMap.containsKey(serviceName)) {
      throw new IllegalArgumentException("Service already registered");
    }
    serviceMap.put(serviceName, service);
  }

  synchronized void unregisterService(Service service) {
    if(!serviceMap.containsKey(service.getDescriptorForType().getFullName())) {
      throw new IllegalArgumentException("Service was not already registered");
    }
    serviceMap.remove(service.getDescriptorForType().getFullName());
  }
}
