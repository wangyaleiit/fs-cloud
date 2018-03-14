package com.fs.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fs.entity.MiniWorkType;
import com.fs.mapper.MiniWorkTypeMapper;
import com.fs.server.BaseService;

/***
 * 工种管理
 * @ClassName workTypeService
 * @Description TODO
 * @author wang.yalei@fujisoft-china.com
 * @date 2017年12月29日
 */
@Service
public class MiniWorkTypeService extends BaseService{

	@Autowired
	private MiniWorkTypeMapper workTypeMapper;
	
	/**
	 * 新增
	 * @param workType
	 * @return
	 */
	public int save(MiniWorkType workType) {
		workType.setId(get32UUID());
		return workTypeMapper.insert(workType);
	}
	
	/**
	 * 删除
	 * @param workType
	 * @return
	 */
	public int del(String id) {
		return workTypeMapper.deleteByKey(id);
	}
	
	
	/**
	 * 更新
	 * @param workType
	 * @return
	 */
	public int update(MiniWorkType workType) {
		return workTypeMapper.update(workType);
	}
	
	/**
	 * 查询
	 * @param workType
	 * @return
	 */
	public List<MiniWorkType> query() {
		return workTypeMapper.queryAllList();
	}
}
