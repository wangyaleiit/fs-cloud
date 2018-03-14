package com.fs.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.fs.entity.WxTest;

/***
 * 流水记账
 * @ClassName wxTest
 * @Description TODO
 * @author wang.yalei@fujisoft-china.com
 * @date 2018年1月29日
 */
@Mapper
public interface WxTestMapper extends BaseMapper<WxTest,String>{
}
