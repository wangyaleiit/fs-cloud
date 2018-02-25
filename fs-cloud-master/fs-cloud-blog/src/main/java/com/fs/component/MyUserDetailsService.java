package com.fs.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;
import com.fs.entity.BlUser;
import com.fs.service.BlUserService;

/***
 * 博客用户管理-逻辑处理信息
 * @ClassName blUserService
 * @Description TODO
 * @author wang.yalei@fujisoft-china.com
 * @date 2018年2月21日
 */
@Component
public class MyUserDetailsService implements UserDetailsService, SocialUserDetailsService {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	
	@Autowired
	private BlUserService blUserService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("表单登录用户名:" + username);
		return buildUser(username);
	}

	@Override
	public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
		logger.info("设计登录用户Id:" + userId);
		return buildUser(userId);
	}

	private BlUser buildUser(String userIdOrName) {
		// 根据用户名查找用户信息
		return blUserService.loadUserByUsername(userIdOrName);
//		String password = passwordEncoder.encode("123456");
//		logger.info("数据库密码是:"+password);
//		return new BlUser();
//		return new SocialUser(userId, password,
//				true, true, true, true,
//				AuthorityUtils.commaSeparatedStringToAuthorityList("admin,ROLE_USER"));
	}

}
