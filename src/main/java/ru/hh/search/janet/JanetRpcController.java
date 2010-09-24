/*
 * Copyright (c) 2009 Stephen Tu <stephen_tu@berkeley.edu>
 * Copyright (c) 2010 Pavel Trukhanov <p.trukhanov@hh.ru>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 */

package ru.hh.search.janet;

import com.google.protobuf.RpcCallback;
import com.google.protobuf.RpcController;

public class JanetRpcController implements RpcController {

	private String reason;
	private boolean failed;
	private boolean canceled;
	@SuppressWarnings("unused")
	private RpcCallback<Object> callback;
	
	public String errorText() {
		return reason;
	}

	public boolean failed() {
		return failed;
	}

	public boolean isCanceled() {
		return canceled;
	}

	public void notifyOnCancel(RpcCallback<Object> callback) {
		this.callback = callback;
	}

	public void reset() {
		reason = null;
		failed = false;
		canceled = false;
		callback = null;
	}

	public void setFailed(String reason) {
		this.reason = reason;
		this.failed = true;
	}

	public void startCancel() {
		canceled = true;
	}

}
