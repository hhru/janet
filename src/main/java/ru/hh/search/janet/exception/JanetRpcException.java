/*
 * Copyright (c) 2009 Stephen Tu <stephen_tu@berkeley.edu>
 * Copyright (c) 2010 Pavel Trukhanov <p.trukhanov@hh.ru>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 */

package ru.hh.search.janet.exception;

import ru.hh.search.janet.JanetRpcRequest;

@SuppressWarnings("serial")
public class JanetRpcException extends Exception {
	
	private final JanetRpcRequest request;
	
	public JanetRpcException(Throwable t, JanetRpcRequest request, String message) {
		this(request, message);
		initCause(t);
	}
	
	public JanetRpcException(JanetRpcRequest request, String message) {
		super(message);
		this.request = request;
	}

  public JanetRpcException(String message, Throwable cause) {
    super(message, cause);
    this.request=null;
  }

  public JanetRpcRequest getRpcRequest() {
		return request;
	}
	
}
