package com.fs.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.fs.support.SecurityConstants;

/***
 * 浏览器表单提交验证基类
 * @ClassName AbstractChannelSecurityConfig
 * @Description TODO
 * @author wang.yalei@fujisoft-china.com
 * @date 2018年1月31日
 */
public class AbstractChannelSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	protected AuthenticationSuccessHandler authenticationSuccessHandler;
	
	@Autowired
	protected AuthenticationFailureHandler authenticationFailureHandler;
	
	protected void applyPasswordAuthenticationConfig(HttpSecurity http) throws Exception {
		http.formLogin()
			.loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
			.loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)
			.successHandler(authenticationSuccessHandler)
			.failureHandler(authenticationFailureHandler);
	}
	
}
