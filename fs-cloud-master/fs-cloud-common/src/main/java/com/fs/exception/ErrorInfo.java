package com.fs.exception;

public class ErrorInfo<T> {
    public static final String OK = "0";
    public static  final String ERROR = "500";

    private String code;
    private String message;
    private String url;
    private String params;
    private String data;
    
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public static String getOk() {
		return OK;
	}
	public static String getError() {
		return ERROR;
	}
}
