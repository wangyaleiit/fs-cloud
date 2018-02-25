package com.fs.entity;

import java.io.Serializable;

import lombok.Data;

/***
 * 日志处理
 * 
 * @ClassName Logger
 * @Description TODO
 * @author wang.yalei@fujisoft-china.com
 * @date 2017年11月27日
 */
@Data
public class Logger implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public Logger() {
		super();
	}

	public Logger(String logType, String methods, String description, String errMsg, String logData, String logIp) {
		this.logType = logType;
		this.methods = methods;
		this.description = description;
		this.errMsg = errMsg;
		this.logData = logData;
		this.logIp = logIp;
	}
	private String id;
	private String logType;
	private String methods;
	private String description;
	private String errMsg;
	private String logData;
	private String logIp;
}
