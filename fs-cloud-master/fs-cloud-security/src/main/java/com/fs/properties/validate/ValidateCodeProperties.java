package com.fs.properties.validate;

import lombok.Data;

/***
 * 验证码
 * @ClassName ValidateCodeProperties
 * @Description TODO
 * @author wang.yalei@fujisoft-china.com
 * @date 2018年1月31日
 */
@Data
public class ValidateCodeProperties {

	private ImageCodeProperties image = new ImageCodeProperties();

	private SmsCodeProperties sms = new SmsCodeProperties();
}
