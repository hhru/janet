/*
 * Copyright (c) 2009 Stephen Tu <stephen_tu@berkeley.edu>
 * Copyright (c) 2010 Pavel Trukhanov <p.trukhanov@hh.ru>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 */

package ru.hh.search.janet.example;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.apache.log4j.BasicConfigurator;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

import ru.hh.search.janet.NettyRpcServer;
import ru.hh.search.janet.example.Calculator.CalcService;

public class CalculatorServer {

	public static void main(String[] args) {
		
		BasicConfigurator.configure();
		
		NettyRpcServer server = new NettyRpcServer(
				new NioServerSocketChannelFactory(
						Executors.newCachedThreadPool(), 
						Executors.newCachedThreadPool()));
		
		server.registerService(CalcService.newReflectiveService(new CalculatorServiceImpl()));
		
		server.registerBlockingService(CalcService.newReflectiveBlockingService(new CalculatorServiceImpl()));
		
		server.serve(new InetSocketAddress(8080));
		
	}
	
}
