package com.fs.properties.browser;

import com.fs.properties.SessionProperties;
import com.fs.support.SecurityConstants;

import lombok.Data;

/****
 * 浏览器访问权限配置
 * @ClassName BrowserProperties
 * @Description TODO
 * @author wang.yalei@fujisoft-china.com
 * @date 2018年1月31日
 */
@Data
public class BrowserProperties {

	private SessionProperties session = new SessionProperties();
	
	private String signUpUrl = "/signUp.html";
	
    /**
     * 退出成功时跳转的url，如果配置了，则跳到指定的url，如果没配置，则返回json数据。
     */
    private String signOutUrl;
	
	private String loginPage = SecurityConstants.DEFAULT_LOGIN_PAGE_URL;
	
	private SecurityConstants.LoginResponseType loginType = SecurityConstants.LoginResponseType.JSON;
	
	private int rememberMeSeconds = 3600;
}
