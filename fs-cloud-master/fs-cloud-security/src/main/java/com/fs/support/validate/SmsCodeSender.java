package com.fs.support.validate;

/***
 * 发送手机验证码
 * @ClassName SmsCodeSender
 * @Description TODO
 * @author wang.yalei@fujisoft-china.com
 * @date 2018年2月1日
 */
public interface SmsCodeSender {
	
	void send(String mobile, String code);
}
