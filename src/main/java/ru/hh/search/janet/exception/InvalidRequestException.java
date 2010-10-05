/*
 * Copyright (c) 2009 Stephen Tu <stephen_tu@berkeley.edu>
 * Copyright (c) 2010 Pavel Trukhanov <p.trukhanov@hh.ru>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 */

package ru.hh.search.janet.exception;

import ru.hh.search.janet.JanetRpcRequest;

@SuppressWarnings("serial")
public class InvalidRequestException extends JanetRpcException {

  public InvalidRequestException(Throwable t, String message) {
    this(t, null, message);
  }

  public InvalidRequestException(Throwable t, JanetRpcRequest request, String message) {
    super(t, request, message);
  }

}
