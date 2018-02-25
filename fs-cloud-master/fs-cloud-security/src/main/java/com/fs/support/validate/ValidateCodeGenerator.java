package com.fs.support.validate;

import org.springframework.web.context.request.ServletWebRequest;

import com.fs.validate.ValidateCode;

/***
 * 手机验证码测试
 * @ClassName ValidateCodeGenerator
 * @Description TODO
 * @author wang.yalei@fujisoft-china.com
 * @date 2018年2月1日
 */
public interface ValidateCodeGenerator {

	ValidateCode generate(ServletWebRequest request);
}
