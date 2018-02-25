package com.fs.properties.validate;

import lombok.Data;

/***
 * 手机验证码
 * @ClassName SmsCodeProperties
 * @Description TODO
 * @author wang.yalei@fujisoft-china.com
 * @date 2018年1月31日
 */
@Data
public class SmsCodeProperties {
	
	private int length = 6;
	private int expireIn = 60;
	private String url;
}
