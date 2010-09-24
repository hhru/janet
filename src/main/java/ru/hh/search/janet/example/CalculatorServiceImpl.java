/*
 * Copyright (c) 2009 Stephen Tu <stephen_tu@berkeley.edu>
 * Copyright (c) 2010 Pavel Trukhanov <p.trukhanov@hh.ru>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 */

package ru.hh.search.janet.example;

import org.apache.log4j.Logger;

import com.google.protobuf.RpcCallback;
import com.google.protobuf.RpcController;
import com.google.protobuf.ServiceException;
import ru.hh.search.janet.example.Calculator.CalcRequest;
import ru.hh.search.janet.example.Calculator.CalcResponse;

public class CalculatorServiceImpl implements Calculator.CalcService.Interface, Calculator.CalcService.BlockingInterface {

	private static final Logger logger = Logger.getLogger(CalculatorServiceImpl.class);
	
	public void add(RpcController controller, CalcRequest request,
			RpcCallback<CalcResponse> done) {
		CalcResponse response = null;
		try {
			response = add(controller, request);
		} catch (ServiceException e) { }
		if (done != null) {
			done.run(response);
		} else {
			// Since no callback registered, might as well spit out the
			// answer here otherwise we'll never know what happened
			if (response == null)
				logger.info("Error occured");
			else
				logger.info("Answer is: " + response.getResult());
		}
	}

	public void divide(RpcController controller, CalcRequest request,
			RpcCallback<CalcResponse> done) {
		CalcResponse response = null;
		try {
			response = divide(controller, request);
		} catch (ServiceException e) { }
		if (done != null) {
			done.run(response);
		} else {
			// Since no callback registered, might as well spit out the
			// answer here otherwise we'll never know what happened
			if (response == null)
				logger.info("Error occured");
			else
				logger.info("Answer is: " + response.getResult());
		}
	}

	public void multiply(RpcController controller, CalcRequest request,
			RpcCallback<CalcResponse> done) {
		CalcResponse response = null;
		try {
			response = multiply(controller, request);
		} catch (ServiceException e) { }
		if (done != null) {
			done.run(response);
		} else {
			// Since no callback registered, might as well spit out the
			// answer here otherwise we'll never know what happened
			if (response == null)
				logger.info("Error occured");
			else
				logger.info("Answer is: " + response.getResult());
		}
	}

	public void subtract(RpcController controller, CalcRequest request,
			RpcCallback<CalcResponse> done) {
		CalcResponse response = null;
		try {
			response = subtract(controller, request);
		} catch (ServiceException e) { }
		if (done != null) {
			done.run(response);
		} else {
			// Since no callback registered, might as well spit out the
			// answer here otherwise we'll never know what happened
			if (response == null)
				logger.info("Error occured");
			else
				logger.info("Answer is: " + response.getResult());
		}
	}

	public CalcResponse add(RpcController controller, CalcRequest request)
			throws ServiceException {
		int answer = request.getOp1() + request.getOp2();
		return CalcResponse.newBuilder().setResult(answer).build();
	}

	public CalcResponse divide(RpcController controller, CalcRequest request)
			throws ServiceException {
		if (request.getOp2() == 0) {
			controller.setFailed("Cannot divide by zero");
			throw new ServiceException("Cannot divide by zero");
		}
		int answer = request.getOp1() / request.getOp2();
		return CalcResponse.newBuilder().setResult(answer).build();
	}

	public CalcResponse multiply(RpcController controller, CalcRequest request)
			throws ServiceException {
		int answer = request.getOp1() * request.getOp2();
		return CalcResponse.newBuilder().setResult(answer).build();
	}

	public CalcResponse subtract(RpcController controller, CalcRequest request)
			throws ServiceException {
		int answer = request.getOp1() - request.getOp2();
		return CalcResponse.newBuilder().setResult(answer).build();
	}

}
