package com.fs.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fs.entity.MiniEmployee;
import com.fs.mapper.MiniEmployeeMapper;
import com.fs.server.BaseService;

/***
 * 雇主发布任务
 * @ClassName EmployerController
 * @Description TODO
 * @author ma.li@fujisoft-china.com
 * @date 2018年1月19日
 */
@Service
public class MiniEmployeeService extends BaseService{

	@Autowired
	private MiniEmployeeMapper employeeMapper;
	
	/**
	 * 保存数据
	 * @param employer
	 * @return
	 */
	public int save(MiniEmployee employer) {
		employer.setId(get32UUID());
		return employeeMapper.insert(employer);
	}
	
	/**
	 * 
	 * @return
	 */
	public List<MiniEmployee> findAllList(){
		return employeeMapper.queryAllList();
	}
	
	
	/**
	 * 根据ID查询数据
	 * @param id
	 * @return
	 */
	public MiniEmployee findById(String id){
		return employeeMapper.queryByKey(id);
	}
	
	/**
	 * 根据openId查询数据
	 * @param id
	 * @return
	 */
	public List<MiniEmployee> findListByKey(String openId){
		return employeeMapper.queryListByKey(openId);
	}
	
	/**
	 * 更新数据
	 * @param employer
	 * @return
	 */
	public int update(MiniEmployee employer){
		return employeeMapper.update(employer);
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public int delete(String id){
		return employeeMapper.deleteByKey(id);
	}
}
