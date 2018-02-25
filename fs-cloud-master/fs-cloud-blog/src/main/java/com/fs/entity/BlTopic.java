package com.fs.entity;

import java.io.Serializable;
import java.util.UUID;
import com.fs.utils.DateUtils;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/***
 * 博客主题实体信息
 * @ClassName blTopic
 * @Description TODO
 * @author wang.yalei@fujisoft-china.com
 * @date 2018年2月13日
 */
@Data
public class BlTopic implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "主健")
    private String id;
    @ApiModelProperty(value = "博客主题")
    private String topicName;
    @ApiModelProperty(value = "是否开启编辑")
    private boolean edit = false;
    @ApiModelProperty(value = "文章数量")
    private int articleNum;
    @ApiModelProperty(value = "创建者")
    private String createUser;
    @ApiModelProperty(value = "创建时间")
    private String createDate;
    @ApiModelProperty(value = "更新者")
    private String updateUser;
    @ApiModelProperty(value = "更新时间")
    private String updateDate;
    
	/**
	 * 插入之前执行方法，需要手动调用
	 */
	public void preInsert(){
		this.id = UUID.randomUUID().toString().trim().replaceAll("-", "");
		this.createUser = this.id;
		this.updateUser = this.id;
		this.updateDate = DateUtils.getTime();
		this.createDate = this.updateDate;
	}
	
	/**
	 * 更新之前执行方法，需要手动调用
	 */
	public void preUpdate(){
		this.updateDate = DateUtils.getTime();
	}
}
