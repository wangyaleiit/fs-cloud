package com.fs.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/***
 * 跨域处理
 * 
 * @ClassName CorsConfig
 * @Description TODO
 * @author wang.yalei@fujisoft-china.com
 * @date 2017年10月25日
 */

 // @Configuration
public class CorsConfig {

//	private CorsConfiguration buildConfig() {
//		CorsConfiguration corsConfiguration = new CorsConfiguration();
//		// 允许任何域名使用
//		corsConfiguration.addAllowedOrigin("*");
//		// 允许任何头
//		corsConfiguration.addAllowedHeader("*");
//		// 允许任何方法
//		corsConfiguration.addAllowedMethod("*");
//		return corsConfiguration;
//	}
//
//	@Bean
//	public CorsFilter corsFilter() {
//		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		source.registerCorsConfiguration("/**", buildConfig());
//		
//		
//        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
//        // 这个顺序很重要哦，为避免麻烦请设置在最前
//        bean.setOrder(0);
//		
//		return new CorsFilter(source);
//	}
	
    // @Bean
    public FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        // 设置允许的网站域名， *为全允许
        config.addAllowedOrigin("*");
        // 设置需要限制 HEADER 或 METHOD
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.setMaxAge(3600L);
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        // 这个顺序很重要哦，为避免麻烦请设置在最前
        bean.setOrder(0);
        return bean;
    }
}
