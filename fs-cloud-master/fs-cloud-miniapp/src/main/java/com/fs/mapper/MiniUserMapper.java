package com.fs.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.fs.entity.MiniAppUser;

/***
 * 小程序用户信息
 * @ClassName EmployerController
 * @Description TODO
 * @author ma.li@fujisoft-china.com
 * @date 2018年1月19日
 */
@Mapper
public interface MiniUserMapper extends BaseMapper<MiniAppUser,String>{
	
	/**
	 * 是否有预约权限
	 * @param openId
	 * @return
	 */
	Integer isSubscribeRole(String openId);
	
	/***
	 * 查询是否已认证
	 * @param openId
	 * @return
	 */
	String getCert(String openId);
}
