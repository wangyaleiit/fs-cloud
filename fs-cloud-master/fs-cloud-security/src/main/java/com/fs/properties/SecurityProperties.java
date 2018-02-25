package com.fs.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.fs.properties.browser.BrowserProperties;
import com.fs.properties.oauth2.OAuth2Properties;
import com.fs.properties.social.SocialProperties;
import com.fs.properties.validate.ValidateCodeProperties;
import lombok.Data;

/****
 * 权限属性基础配置
 * @ClassName SecurityProperties
 * @Description TODO
 * @author wang.yalei@fujisoft-china.com
 * @date 2018年1月31日
 */
@Data
@ConfigurationProperties(prefix = "fs.security")
public class SecurityProperties {

    /**
     * 浏览器端配置
     */
	private BrowserProperties browser = new BrowserProperties();
	
    /**
     * 验证码配置
     */
	private ValidateCodeProperties code = new ValidateCodeProperties();
	
    /**
     * 社交登录相关配置
     */
	private SocialProperties social = new SocialProperties();
	
    /**
     * OAuth2认证服务器配置
     */
    private OAuth2Properties oauth2 = new OAuth2Properties();
}
