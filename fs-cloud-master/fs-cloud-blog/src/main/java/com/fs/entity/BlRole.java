package com.fs.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class BlRole implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
    private String name;
    private String nameZh;
}
