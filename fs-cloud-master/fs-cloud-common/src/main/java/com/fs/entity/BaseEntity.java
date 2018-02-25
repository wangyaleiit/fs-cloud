package com.fs.entity;

import java.util.Date;
import java.util.UUID;

/***
 * @ClassName BootStrapTable
 * @Description TODO
 * @author wang.yalei@fujisoft-china.com
 * @date 2017年10月11日
 */
public abstract class BaseEntity {

	// 主键
	protected String id;
	// 创建者
	protected String createBy;
	// 创建日期
	protected Date createDate;
	// 更新者
	protected String updateBy;
	// 更新日期
	protected Date updateDate;
	// 备注
	protected String remarks;
	// 删除标记（0：正常；1：删除）
	protected char delFlag = '0';
	
	/**
	 * 插入之前执行方法，需要手动调用
	 */
	public void preInsert(){
		this.id = UUID.randomUUID().toString().trim().replaceAll("-", "");
		this.createBy = this.id;
		this.updateBy = this.id;
		this.updateDate = new Date();
		this.createDate = this.updateDate;
	}
	
	/**
	 * 更新之前执行方法，需要手动调用
	 */
	public void preUpdate(String updateBy){
		this.updateBy = updateBy;
		this.updateDate = new Date();
	}
}
