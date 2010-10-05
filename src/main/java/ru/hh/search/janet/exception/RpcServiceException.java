/*
 * Copyright (c) 2009 Stephen Tu <stephen_tu@berkeley.edu>
 * Copyright (c) 2010 Pavel Trukhanov <p.trukhanov@hh.ru>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 */

package ru.hh.search.janet.exception;

import com.google.protobuf.ServiceException;
import ru.hh.search.janet.JanetRpcRequest;


public class RpcServiceException extends JanetRpcException {

	public RpcServiceException(ServiceException serviceException, JanetRpcRequest request, String message) {
		super(serviceException, request, message);
	}

  public RpcServiceException(JanetRpcRequest request, String message) {
		super(request, message);
	}
}
