/*
 * Copyright (c) 2009 Stephen Tu <stephen_tu@berkeley.edu>
 * Copyright (c) 2010 Pavel Trukhanov <p.trukhanov@hh.ru>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 */
package ru.hh.search.janet;

import com.google.protobuf.Message;
import org.jboss.netty.handler.codec.http.HttpVersion;

public class JanetRpcRequest {
  
  public JanetRpcRequest(String serviceName, String methodName, Message requestMessage, HttpVersion version) {
    this.version = version;
    this.serviceName = serviceName;
    this.methodName = methodName;
    this.requestMessage = requestMessage;
  }

  public HttpVersion version;
  public String serviceName;
  public String methodName;
  public Message requestMessage;

}
