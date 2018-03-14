package com.fs.web;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import me.chanjar.weixin.common.exception.WxErrorException;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fs.entity.MiniAppUser;
import com.fs.service.MiniUserService;
import com.fs.utils.JsonUtils;
import com.fs.utils.R;

/***
 * 微信登陆
 * @ClassName WxMaUserController
 * @Description TODO
 * @author wang.yalei@fujisoft-china.com
 * @date 2018年1月23日
 */
@RestController
@RequestMapping("/miniApp/user")
public class WxMaUserController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private WxMaService wxService;
	
	@Autowired
	private MiniUserService userService;

	/**
	 * 登陆接口
	 */
	@PostMapping("login")
	public String login(String code) {
		if (StringUtils.isBlank(code)) {
			return "empty jscode";
		}
		try {
			WxMaJscode2SessionResult session = this.wxService.getUserService().getSessionInfo(code);
			this.logger.info(session.getSessionKey());
			this.logger.info("openId" + session.getOpenid());
			this.logger.info(session.getExpiresin().toString());
			return JsonUtils.toJson(session);
		} catch (WxErrorException e) {
			this.logger.error(e.getMessage(), e);
			return e.toString();
		}
	}

	/**
	 * <pre>
	 * 获取用户信息接口
	 * </pre>
	 */
	@GetMapping("info")
	public String info(String sessionKey, String signature, String rawData, String encryptedData, String iv) {
		// 用户信息校验
		if (!this.wxService.getUserService().checkUserInfo(sessionKey, rawData, signature)) {
			return "user check failed";
		}
		// 解密用户信息
		WxMaUserInfo userInfo = this.wxService.getUserService().getUserInfo(sessionKey, encryptedData, iv);
		return JsonUtils.toJson(userInfo);
	}

	/****
	 * 用户授权登陆+获取用户信息
	 * @param code
	 * @param encryptedData
	 * @param iv
	 * @return
	 */
	@PostMapping("userInfo")
	public String loginUserInfo(String code, String encryptedData, String iv) {
		if (StringUtils.isBlank(code)) {
			return "empty jscode";
		}
		try {
			WxMaJscode2SessionResult session = this.wxService.getUserService().getSessionInfo(code);
			WxMaUserInfo userInfo = this.wxService.getUserService().getUserInfo(session.getSessionKey(), encryptedData,iv);
			List<MiniAppUser> appUserList = userService.findListByKey(userInfo.getOpenId());
			if(appUserList.size() == 0){
				MiniAppUser appUser = new MiniAppUser();
				appUser.setWxMaUserInfo(userInfo);
				userService.save(appUser);
			} 
			return JsonUtils.toJson(userInfo);
		} catch (WxErrorException e) {
			e.printStackTrace();
			this.logger.error(e.getMessage(), e);
			return e.toString();
		}
	}
	
	/**
	 * 更新
	 * @param employer
	 * @return
	 */
	@PutMapping("/update")
	public R update(@RequestBody MiniAppUser miniAppUser){
		int count = userService.update(miniAppUser);
		if(count > 0){
			return R.ok();
		}
		return R.error();
	}
	
	/***
	 * 查询是否已认证
	 */
	@GetMapping("/getCert/{openId}")
	public String getCert(@PathVariable String openId){
		return userService.getCert(openId);
	}
}
