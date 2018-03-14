package com.fs.entity;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;

/***
 * 流水记账实体信息
 * @ClassName wxTest
 * @Description TODO
 * @author wang.yalei@fujisoft-china.com
 * @date 2018年1月30日
 */
@Data
public class WxTest implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank
    @ApiModelProperty(value = "主健")
    private String id;
    @ApiModelProperty(value = "微信主健")
    private String openId;
    @ApiModelProperty(value = "序号")
    private String bookNumber;
    @ApiModelProperty(value = "流水日期")
    @DateTimeFormat(pattern="yyyy-mm-dd HH:mm:ss ")
    @JsonFormat(pattern="yyyy-mm-dd HH:mm:ss ",timezone = "GMT + 8 ")
    private Date bookDate;
    @ApiModelProperty(value = "记账类型")
    private String bookType;
    @ApiModelProperty(value = "金额")
    private String bookMoney;
    @ApiModelProperty(value = "明细")
    private String bookDetail;
    @ApiModelProperty(value = "创建者")
    private String createBy;
    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern="yyyy-mm-dd HH:mm:ss ")
    @JsonFormat(pattern="yyyy-mm-dd HH:mm:ss ",timezone = "GMT + 8 ")
    private Date createDate;
    @ApiModelProperty(value = "更新者")
    private String updateBy;
    @ApiModelProperty(value = "更新时间")
    @DateTimeFormat(pattern="yyyy-mm-dd HH:mm:ss ")
    @JsonFormat(pattern="yyyy-mm-dd HH:mm:ss ",timezone = "GMT + 8 ")
    private Date updateDate;
    @ApiModelProperty(value = "删除标记")
    private char delFlag;
}
