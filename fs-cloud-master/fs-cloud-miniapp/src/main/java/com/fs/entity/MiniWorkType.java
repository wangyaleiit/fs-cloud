package com.fs.entity;

import java.io.Serializable;

import lombok.Data;

/***
 * 工种类别
 * @ClassName WxMenuCustom
 * @Description TODO
 * @author wang.yalei@fujisoft-china.com
 * @date 2017年12月13日
 */
@Data
public class MiniWorkType implements Serializable{

	private static final long serialVersionUID = 1L;

	private String id;
	private String typeName;
	private String typeMoney;
}
