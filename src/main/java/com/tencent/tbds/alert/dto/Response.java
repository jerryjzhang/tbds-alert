package com.tencent.tbds.alert.dto;

import org.springframework.http.HttpStatus;

public class Response<T>{

	private int resultCode;
	private String message;
	private T resultData;
	public int getResultCode() {
		return resultCode;
	}
	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getResultData() {
		return resultData;
	}
	public void setResultData(T resultData) {
		this.resultData = resultData;
	}
	
	public static<T> Response<T> wrapResult(T result) {
    Response Response = new Response();
    Response.setResultCode(HttpStatus.OK.value());
    Response.setMessage(HttpStatus.OK.getReasonPhrase());
    Response.setResultData(result);
    return Response;
  }
}