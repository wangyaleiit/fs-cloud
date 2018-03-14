package com.fs.entity;

import java.io.Serializable;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/***
 * 雇主信息发布
 * @ClassName MiniEmployer
 * @Description TODO
 * @author wang.yalei@fujisoft-china.com
 * @date 2018年1月22日
 */
@Data
public class MiniEmployer implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String openId;
	private String chooseAres;
	private String chooseAddress;
	private String address;
	private String tel;
	private String startDate;
	private double typeMoney;
	private String workType;
	private String tradeNo;
	private String payStatus;
	private String flag;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")  
	private Date createTime;
	private String refundTime;
}
