package com.fs.entity;

import java.io.Serializable;
import java.util.UUID;

import com.fs.utils.DateUtils;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/***
 * 博客文章实体信息
 * @ClassName blArticle
 * @Description TODO
 * @author wang.yalei@fujisoft-china.com
 * @date 2018年2月13日
 */
@Data
public class BlArticle implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主健")
    private String id;
    @ApiModelProperty(value = "文章索引")
    private int artIndex;
    @ApiModelProperty(value = "博客标题")
    private String title;
    @ApiModelProperty(value = "博客主题")
    private String topic;
    private String topicName;
    @ApiModelProperty(value = "博客标签")
    private String[] dynamicTags;
    private String tag;
    @ApiModelProperty(value = "博客内容")
    private String mdcontent;
    @ApiModelProperty(value = "html源码")
    private String htmlcontent;
    @ApiModelProperty(value = "文章摘要")
    private String summary;
    @ApiModelProperty(value = "0:草稿箱;1:已发表;2:回收站;")
    private String flag;
    @ApiModelProperty(value = "浏览量")
    private int pageview = 0;
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
	
	public String[] getTagArray(){
		return tag != null ? tag.split(",") : null;
	}
}
