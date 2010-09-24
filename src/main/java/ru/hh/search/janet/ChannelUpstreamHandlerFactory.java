/*
 * Copyright (c) 2009 Stephen Tu <stephen_tu@berkeley.edu>
 * Copyright (c) 2010 Pavel Trukhanov <p.trukhanov@hh.ru>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 */
package ru.hh.search.janet;

import org.jboss.netty.channel.ChannelUpstreamHandler;

public interface ChannelUpstreamHandlerFactory {

	public ChannelUpstreamHandler getChannelUpstreamHandler();
	
}
