package com.fs.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fs.entity.MiniEmployer;
import com.fs.mapper.MiniEmployerMapper;
import com.fs.server.BaseService;

/***
 * 雇主发布任务
 * @ClassName EmployerController
 * @Description TODO
 * @author ma.li@fujisoft-china.com
 * @date 2018年1月19日
 */
@Service
public class MiniEmployerService extends BaseService{

	@Autowired
	private MiniEmployerMapper employerMapper;
	
	/**
	 * 保存数据
	 * @param employer
	 * @return
	 */
	public int save(MiniEmployer employer) {
		employer.setId(get32UUID());
		return employerMapper.insert(employer);
	}
	
	/**
	 * 
	 * @return
	 */
	public List<MiniEmployer> findAllList(){
		return employerMapper.queryAllList();
	}
	
	
	/**
	 * 根据ID查询数据
	 * @param id
	 * @return
	 */
	public MiniEmployer findById(String id){
		return employerMapper.queryByKey(id);
	}
	
	/**
	 * 根据openId查询数据
	 * @param id
	 * @return
	 */
	public List<MiniEmployer> findListByKey(String openId){
		return employerMapper.queryListByKey(openId);
	}
	
	/**
	 * 更新数据
	 * @param employer
	 * @return
	 */
	public int update(MiniEmployer employer){
		return employerMapper.update(employer);
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public int delete(String id){
		return employerMapper.deleteByKey(id);
				
	}
}
