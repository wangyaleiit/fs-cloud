package com.fs.entity;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/***
 * 钟点工信息发布
 * @ClassName MiniEmployer
 * @Description TODO
 * @author wang.yalei@fujisoft-china.com
 * @date 2018年1月22日
 */
@Data
@ApiModel
public class MiniEmployee implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "主键")
	private String id;
	@ApiModelProperty(value = "openId")
	private String openId;
	@ApiModelProperty(value = "开始时间")
	private String startTime;
	private String endTime;
	private String regionId;
	private String regionText;
	private String tel;
	private String remarks;
	private String isShow;
}
