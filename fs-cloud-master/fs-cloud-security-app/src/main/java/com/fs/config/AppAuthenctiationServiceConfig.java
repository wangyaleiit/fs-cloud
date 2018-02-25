package com.fs.config;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import com.fs.properties.SecurityProperties;
import com.fs.properties.oauth2.OAuth2ClientProperties;

/****
 * 
 * @author dell
 *  https://tools.ietf.org/html/rfc6749#section-4.1.3
 *  http://localhost:8888/oauth/authorize?response_type=code&client_id=wylBlog&redirect_uri=http://example.com&scope=all
 */
@Configuration
@EnableAuthorizationServer
public class AppAuthenctiationServiceConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private SecurityProperties securityProperties;
	
    @Autowired
    private TokenStore tokenStore;

    @Autowired(required = false)
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Autowired(required = false)
    private TokenEnhancer jwtTokenEnhancer;
    
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore)
        .authenticationManager(authenticationManager)
        .userDetailsService(userDetailsService);
		// 如果jwt转换器和jwt增强器都存在
		if (jwtAccessTokenConverter != null && jwtTokenEnhancer != null) {
		    // 将其加入到增强链上
		    TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
		    List<TokenEnhancer> enhancers = new ArrayList<>();
		    enhancers.add(jwtTokenEnhancer);
		    enhancers.add(jwtAccessTokenConverter);
		    enhancerChain.setTokenEnhancers(enhancers);
		    endpoints.tokenEnhancer(enhancerChain)
		            .accessTokenConverter(jwtAccessTokenConverter);
		}
    }

    /**
     * 对多个应用进行授权配置
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 存储在内存中
        InMemoryClientDetailsServiceBuilder builder = clients.inMemory();
        if (ArrayUtils.isNotEmpty(securityProperties.getOauth2().getClients())) {
            for (OAuth2ClientProperties config : securityProperties.getOauth2().getClients()) {
                builder.withClient(config.getClientId())
                        .secret(config.getClientSecret())
                        .accessTokenValiditySeconds(config.getAccessTokenValidateSeconds())
                        .authorizedGrantTypes("refresh_token", "authorization_code", "password")
                        // 设置刷新令牌的过期时间
                        .refreshTokenValiditySeconds(2592000)
                        .scopes("all", "write", "read");
            }
        }
    }
}
