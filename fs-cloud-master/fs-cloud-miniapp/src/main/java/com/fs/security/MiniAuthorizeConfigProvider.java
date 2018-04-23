package com.fs.security;

import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;
import com.fs.authorize.AuthorizeConfigProvider;


@Component
@Order(Integer.MAX_VALUE)
public class MiniAuthorizeConfigProvider implements AuthorizeConfigProvider {

    @Override
    public boolean config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        config.antMatchers("/miniApp/*").permitAll();
        config.antMatchers("/static/*").permitAll();
        return true;
    }
}
