package com.fs.properties.oauth2;

import lombok.Data;

/**
 * oauth2配置
 */
@Data
public class OAuth2Properties {
	
    /**
     * jwt的签名
     */
    private String jwtSigningKey = "wangyl";

    /**
     * 客户端配置
     */
    private OAuth2ClientProperties[] clients = {};

}
