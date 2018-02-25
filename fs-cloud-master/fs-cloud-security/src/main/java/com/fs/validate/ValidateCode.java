package com.fs.validate;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/***
 * 验证码公共处理类
 * @ClassName ValidateCode
 * @Description TODO
 * @author wang.yalei@fujisoft-china.com
 * @date 2018年2月1日
 */
@Data
public class ValidateCode implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String code;
	
	private LocalDateTime expireTime;
	
	public ValidateCode(String code, int expireIn){
		this.code = code;
		this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
	}
	
	public ValidateCode(String code, LocalDateTime expireTime){
		this.code = code;
		this.expireTime = expireTime;
	}
	
	/**
	 *  判断过期时间
	 * @return
	 */
	public boolean isExpried() {
		return LocalDateTime.now().isAfter(expireTime);
	}
}
