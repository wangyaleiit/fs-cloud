package com.fs.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fs.entity.MiniAppUser;
import com.fs.mapper.MiniUserMapper;
import com.fs.server.BaseService;

/***
 * 雇主发布任务
 * @ClassName EmployerController
 * @Description TODO
 * @author ma.li@fujisoft-china.com
 * @date 2018年1月19日
 */
@Service
public class MiniUserService extends BaseService{

	@Autowired
	private MiniUserMapper userMapper;
	
	/**
	 * 保存数据
	 * @param employer
	 * @return
	 */
	public int save(MiniAppUser appUser) {
		appUser.setId(get32UUID());
		return userMapper.insert(appUser);
	}
	
	/**
	 * 根据ID查询数据
	 * @param id
	 * @return
	 */
	public MiniAppUser findById(String id){
		return userMapper.queryByKey(id);
	}
	
	/**
	 * 根据openId查询数据
	 * @param id
	 * @return
	 */
	public List<MiniAppUser> findListByKey(String openId){
		return userMapper.queryListByKey(openId);
	}
	
	/**
	 * 查询所有用户信息
	 * @return
	 */
	public List<MiniAppUser> findAllList(){
		return userMapper.queryAllList();
	}
	
	/***
	 * 查询是否有预约权限 
	 * @param openId
	 * @return
	 */
	public int isSubscribeRole(String openId){
		return userMapper.isSubscribeRole(openId);
	}
	
	
	/***
	 * 查询是否已认证
	 * @param openId
	 * @return
	 */
	public String getCert(String openId){
		return userMapper.getCert(openId);
	}
	
	
	/**
	 * 更新数据
	 * @param employer
	 * @return
	 */
	public int update(MiniAppUser employer){
		return userMapper.update(employer);
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public int delete(String id){
		return userMapper.deleteByKey(id);
				
	}
}
