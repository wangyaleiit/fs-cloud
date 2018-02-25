package com.fs.properties.validate;

import lombok.Data;
import lombok.EqualsAndHashCode;

/***
 * 图形验证码
 * @ClassName ImageCodeProperties
 * @Description TODO
 * @author wang.yalei@fujisoft-china.com
 * @date 2018年1月31日
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class ImageCodeProperties extends SmsCodeProperties {
	
	public ImageCodeProperties() {
		setLength(4);
	}
	private int width = 67;
	private int height = 23;
}
