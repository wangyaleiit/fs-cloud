package com.fs.validate;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fs.exception.ValidateCodeException;
import com.fs.support.validate.ValidateCodeProcessor;
import com.fs.support.validate.ValidateCodeType;

/***
 * 验证码处理器
 * @ClassName ValidateCodeProcessorHolder
 * @Description TODO
 * @author wang.yalei@fujisoft-china.com
 * @date 2018年2月1日
 */
@Component
public class ValidateCodeProcessorHolder {

    @Autowired
    private Map<String, ValidateCodeProcessor> validateCodeProcessors;

    public ValidateCodeProcessor findValidateCodeProcessor(ValidateCodeType type) {
        return findValidateCodeProcessor(type.toString().toLowerCase());
    }

	public ValidateCodeProcessor findValidateCodeProcessor(String type) {
		String name = type.toLowerCase() + ValidateCodeProcessor.class.getSimpleName();
		ValidateCodeProcessor processor = validateCodeProcessors.get(name);
		if (processor == null) {
			throw new ValidateCodeException("验证码处理器" + name + "不存在");
		}
		return processor;
	}

}
