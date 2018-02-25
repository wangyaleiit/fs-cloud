package com.fs.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

/***
 * 基础Mapper
 * @ClassName BaseMapper
 * @Description TODO
 * @author wang.yalei@fujisoft-china.com
 * @date 2017年11月21日
 * @param <T>
 * @param <ID>
 */
public interface BaseMapper<T, ID> {

	/**
	 * 根据主键查询
	 * @param key
	 * @return
	 */
	T queryByKey(ID key);
	
	/***
	 * 多条件查询
	 * @param columnMap
	 * @return
	 */
	List<T> queryByMap(Map<String, Object> columnMap);
	
	 /**
	 * 查询全部列表
	 */
	 List<T> queryAllList();
	 
	 /**
	 * 根据特定条件 查询列表
	 */
	 List<T> queryList(Object object);
	 
	 /***
	 * 根据主键查询所有数据
	 * @return
	 */
	 List<T> queryListByKey(ID key);
	 
	/**
	 * 插入数据
	 * @param entity
	 * @return
	 */
	 int insert(T entity);
	
	/**
	 * 更新数据
	 * @param entity
	 * @return
	 */
	 int update(T entity);
	
	 /***
	  * 根据非主键删除数据
	  * @param columnMap
	  * @return
	  */
	 int deleteByMap(Map<String, Object> columnMap);
	 
	 /***
	  * 根据主键批量删除
	  * @param idList
	  * @return
	  */
	 int deleteBatchIds(@Param("ids") String[] ids);
	 
	 /***
	  * 根据非主键删除数据
	  * @param columnMap
	  * @return
	  */
	 int deleteByObj(T entity);
	 
     /***
      * 删除所有
      * @return
      */
	 int deleteAll();
	 
	 /***
	  * 批量删除
	  * @return
	  */
     int deleteBatch(Object[] object);
}