package com.fs.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import com.fs.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.fs.authorize.AuthorizeConfigManager;
import com.fs.config.validate.ValidateCodeSecurityConfig;
import com.fs.support.SecurityConstants;

/***
 * 资源管理器
 * @ClassName FsAuthenctiationFailureHandler
 * @Description TODO
 * @author wang.yalei@fujisoft-china.com
 * @date 2018年1月31日
 */
@Configuration
@EnableResourceServer
public class AppResourceConfig extends ResourceServerConfigurerAdapter{
	
    @Autowired
    private AuthenticationFailureHandler customAuthenctiationFailureHandler;

    @Autowired
    private AuthenticationSuccessHandler customAuthenticationSuccessHandler;
    
    @Autowired
    private AuthorizeConfigManager authorizeConfigManager;
    
    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;
    
    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;
    
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
                .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)
                .successHandler(customAuthenticationSuccessHandler)
                .failureHandler(customAuthenctiationFailureHandler);
        http
                 //应用验证码安全配置
                .apply(validateCodeSecurityConfig)
                .and()
                 //应用短信验证码认证安全配置
                .apply(smsCodeAuthenticationSecurityConfig)
//                .and()
//                // 引用社交配置
//                .apply(fsSocialConfig)
//                .and()
//
//                .apply(openIdAuthenticationSecurityConfig)
//                .and()
//                .requestMatchers().antMatchers(HttpMethod.OPTIONS, "/**") 
                .and()
                .csrf().disable();
 

        // 引用默认配置
        authorizeConfigManager.config(http.authorizeRequests());
    }
}
