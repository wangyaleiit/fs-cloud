package com.fs.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.fs.entity.BlArticle;

/***
 * 博客文章
 * @ClassName blArticle
 * @Description TODO
 * @author wang.yalei@fujisoft-china.com
 * @date 2018年2月13日
 */
@Mapper
public interface BlArticleMapper extends BaseMapper<BlArticle,String>{

	int toDust(@Param("ids") String[] split);

	BlArticle queryPerInfo(int artIndex);
	
	BlArticle queryNextInfo(int artIndex);
	
	List<Map<String,String>> queryTimeLine();
}