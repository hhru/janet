/*
 * Copyright (c) 2009 Stephen Tu <stephen_tu@berkeley.edu>
 * Copyright (c) 2010 Pavel Trukhanov <p.trukhanov@hh.ru>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 */

package ru.hh.search.janet.exception;

import ru.hh.search.janet.JanetRpcRequest;

@SuppressWarnings("serial")
public class NoSuchServiceMethodException extends JanetRpcException {

	public NoSuchServiceMethodException(JanetRpcRequest request) {
		super(request, "No such method: " + request.methodName);
	}

  public NoSuchServiceMethodException(Throwable t, String message) {
		super(t, null, message);
	}
}
