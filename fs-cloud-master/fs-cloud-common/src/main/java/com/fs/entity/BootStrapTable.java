package com.fs.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import lombok.Data;

/***
 * 分页处理
 * @ClassName BootStrapTable
 * @Description TODO
 * @author wang.yalei@fujisoft-china.com
 * @date 2017年10月11日
 */
@Data
public class BootStrapTable implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer pageNumber = 1;
	private Integer pageSize = 10;
	private String searchText;
	private String queryParams;
	private Map<String, Object> paramsMap = new HashMap<String,Object>();
}
