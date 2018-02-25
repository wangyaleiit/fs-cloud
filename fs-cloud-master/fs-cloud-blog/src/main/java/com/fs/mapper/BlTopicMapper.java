package com.fs.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.fs.entity.BlTopic;

/***
 * 博客主题
 * @ClassName blTopic
 * @Description TODO
 * @author wang.yalei@fujisoft-china.com
 * @date 2018年2月13日
 */
@Mapper
public interface BlTopicMapper extends BaseMapper<BlTopic,String>{
}