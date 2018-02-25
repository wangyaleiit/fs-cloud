package com.fs.entity;

import java.io.Serializable;
import java.util.List;

/***
 * iview组件
 * @ClassName Cascader
 * @Description TODO
 * @author wang.yalei@fujisoft-china.com
 * @date 2017年12月6日
 */
public class IviewComponent implements Serializable{

	private static final long serialVersionUID = 1L;

	private String value;
	
	private String label;
	
	private List<IviewComponent> children;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public List<IviewComponent> getChildren() {
		return children;
	}

	public void setChildren(List<IviewComponent> children) {
		this.children = children;
	}
}
