package com.fs.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.fs.entity.MiniEmployer;

/***
 * 雇主发布任务
 * @ClassName EmployerController
 * @Description TODO
 * @author ma.li@fujisoft-china.com
 * @date 2018年1月19日
 */
@Mapper
public interface MiniEmployerMapper extends BaseMapper<MiniEmployer,String>{
}
