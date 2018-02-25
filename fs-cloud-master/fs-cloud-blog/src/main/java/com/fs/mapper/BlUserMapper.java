package com.fs.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.fs.entity.BlUser;

/***
 * 博客用户管理
 * @ClassName blUser
 * @Description TODO
 * @author wang.yalei@fujisoft-china.com
 * @date 2018年2月21日
 */
@Mapper
public interface BlUserMapper extends BaseMapper<BlUser,String>{

	BlUser loadUserByUsername(String userIdOrName);
}