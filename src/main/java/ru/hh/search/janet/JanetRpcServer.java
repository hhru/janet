/*
 * Copyright (c) 2009 Stephen Tu <stephen_tu@berkeley.edu>
 * Copyright (c) 2010 Pavel Trukhanov <p.trukhanov@hh.ru>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 */
package ru.hh.search.janet;

import com.google.protobuf.Service;
import java.net.SocketAddress;
import org.apache.log4j.Logger;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.ChannelUpstreamHandler;


public class JanetRpcServer {
  private static final Logger logger = Logger.getLogger(NettyRpcServer.class);

  private final ServerBootstrap bootstrap;

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
    bootstrap = new ServerBootstrap(channelFactory);
    bootstrap.setPipelineFactory(pipelineFactory);
  }

  public void registerService(Service service) {
    handler.registerService(service);
    decoderFactory.registerService(service);
  }

  public void unregisterService(Service service) {
    handler.unregisterService(service);
    decoderFactory.unregisterService(service);
  }

  /*public void serve() {
    logger.info("Serving...");
    bootstrap.bind();
  }*/

  public void serve(SocketAddress sa) {
    logger.info("Serving on: " + sa);
    bootstrap.bind(sa);
  }

}


