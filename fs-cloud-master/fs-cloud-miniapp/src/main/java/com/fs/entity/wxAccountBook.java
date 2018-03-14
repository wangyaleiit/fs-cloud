package com.fs.entity;

import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import lombok.Data;

/***
 * 流水记账实体信息
 * @ClassName wxAccountBook
 * @Description TODO
 * @author wang.yalei@fujisoft-china.com
 * @date 2018年1月26日
 */
@Data
public class wxAccountBook implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String openId;
    private String bookNumber;
    @DateTimeFormat(pattern="yyyy - mm - dd HH: mm: ss ")
    @JsonFormat(pattern="yyyy - mm - dd HH: mm: ss ",timezone = "GMT + 8 ")
    private Date bookDate;
    private String bookType;
    private String bookMoney;
    private String bookDetail;
    private String createBy;
    @DateTimeFormat(pattern="yyyy - mm - dd HH: mm: ss ")
    @JsonFormat(pattern="yyyy - mm - dd HH: mm: ss ",timezone = "GMT + 8 ")
    private Date createDate;
    private String updateBy;
    @DateTimeFormat(pattern="yyyy - mm - dd HH: mm: ss ")
    @JsonFormat(pattern="yyyy - mm - dd HH: mm: ss ",timezone = "GMT + 8 ")
    private Date updateDate;
    private String remarks;
    private char delFlag;
}
