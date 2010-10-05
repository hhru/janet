/*
 * Copyright (c) 2009 Stephen Tu <stephen_tu@berkeley.edu>
 * Copyright (c) 2010 Pavel Trukhanov <p.trukhanov@hh.ru>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 */
package ru.hh.search.janet;

import com.google.protobuf.Message;
import org.jboss.netty.handler.codec.http.HttpVersion;

public class JanetRpcResponse {
  public enum ErrorCode {

    // Server-side errors
    BAD_REQUEST_DATA,
    // Server received bad request data
    BAD_REQUEST_PROTO,
    // Server received bad request proto
    SERVICE_NOT_FOUND,
    // Service not found on server
    METHOD_NOT_FOUND,
    // Method not found on server
    RPC_ERROR,
    // Rpc threw exception on server
    RPC_FAILED,
    // Rpc failed on server

    // Client-side errors (these are returned by the client-side code)
    INVALID_REQUEST_PROTO,
    // Rpc was called with invalid request proto
    BAD_RESPONSE_PROTO,
    // Server returned a bad response proto
    UNKNOWN_HOST,
    // Could not find supplied host
    IO_ERROR; // I/O error while communicating with server

  }

  public Message responseMessage;
  HttpVersion version;

  public Message requestMessage;
  public String errorMessage;
  public ErrorCode errCode;

  public JanetRpcResponse(Message responseMessage, HttpVersion version) {
    this.version = version;
    this.responseMessage = responseMessage;
    this.errCode = null;
    this.errorMessage = null;
  }

  public void setErrCode(ErrorCode errCode) {
    this.errCode = errCode;
  }

  public void setErrorMessage(String errorMessage) {

    this.errorMessage = errorMessage;
  }

  public JanetRpcResponse(Message requestMessage, String errorMessage, ErrorCode errCode, HttpVersion version) {
    this.version = version;
    this.requestMessage = requestMessage;
    this.errorMessage = errorMessage;
    this.errCode = errCode;
    this.responseMessage = null;
  }
}
