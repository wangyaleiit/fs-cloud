package com.fs.entity;

import java.io.Serializable;

import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import lombok.Data;

/***
 * 小程序用户信息
 * @ClassName MiniAppUser
 * @Description TODO
 * @author wang.yalei@fujisoft-china.com
 * @date 2018年1月22日
 */
@Data
public class MiniAppUser implements Serializable{

	private static final long serialVersionUID = 1L;
	private String id;
	private String openId;
	private String certFlag;
	private WxMaUserInfo wxMaUserInfo;
	private String userType;
	private String realName;
	private String cardNo;
	private String phoneNum;
}
