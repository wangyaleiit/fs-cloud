package com.fs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/***
 * 启动入口
 * @ClassName App
 * @author wang.yalei@fujisoft-china.com
 * @date 2017年11月16日
 */
@SpringBootApplication
public class App extends SpringBootServletInitializer{
	
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
	
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(App.class);
    }
}