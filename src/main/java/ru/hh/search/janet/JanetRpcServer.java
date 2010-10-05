/*
 * Copyright (c) 2009 Stephen Tu <stephen_tu@berkeley.edu>
 * Copyright (c) 2010 Pavel Trukhanov <p.trukhanov@hh.ru>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 */
package ru.hh.search.janet;

import com.google.protobuf.Descriptors.MethodDescriptor;
import com.google.protobuf.Service;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.ChannelUpstreamHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class JanetRpcServer extends ServerBootstrap{
  private static final Logger logger = LoggerFactory.getLogger(JanetRpcServer.class);

  private JanetHttpProtobufDecoderFactory decoderFactory = new JanetHttpProtobufDecoderFactory();

  private final JanetRpcServerChannelUpstreamHandler handler = new JanetRpcServerChannelUpstreamHandler();
  private final ChannelUpstreamHandlerFactory handlerFactory = new ChannelUpstreamHandlerFactory() {
    public ChannelUpstreamHandler getChannelUpstreamHandler() {
      return handler;
    }
  };

  private final ChannelPipelineFactory pipelineFactory = new JanetRpcPipelineFactory(
      handlerFactory,
      decoderFactory);

  public JanetRpcServer(ChannelFactory channelFactory) {
    super(channelFactory);
    this.setPipelineFactory(pipelineFactory);
  }

  public void registerService(Service service) {
    //String serviceName = service.getDescriptorForType().getFullName();
    String serviceName = service.getDescriptorForType().getName();
    logger.info("Registering service: "+ serviceName);

    for (MethodDescriptor md : service.getDescriptorForType().getMethods()){
      logger.info("With methods: " + md.getName());
    }

    handler.registerService(service);
    decoderFactory.registerService(service);
  }

  public void unregisterService(Service service) {
    handler.unregisterService(service);
    decoderFactory.unregisterService(service);
  }

  public void serve() {
    this.serve(new InetSocketAddress((Integer)getOption("localAddress")));
  }

  public void serve(SocketAddress sa) {
    logger.info("Serving on: " + sa);
    this.bind(sa);
  }

}


