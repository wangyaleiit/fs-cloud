package com.fs.entity;

import java.util.ArrayList;
import java.util.List;

/***
 * 分页处理
 * @ClassName Datagrid
 * @Description TODO
 * @author wang.yalei@fujisoft-china.com
 * @date 2017年11月21日
 */
public class Datagrid {

	private long total;  
	
	private List<?> rows = new ArrayList<>();
	
	private boolean success;

	public Datagrid(long total, List<?> rows) {
		super();
		this.total = total;
		this.rows = rows;
		this.success = true;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public List<?> getRows() {
		return rows;
	}
	public void setRows(List<?> rows) {
		this.rows = rows;
	} 
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
}
