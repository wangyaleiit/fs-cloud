package com.fs.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

/***
 * 数据配置，可配置多个数据源
* @ClassName: DBSourceConfiguration  
* @author 王亚磊   
* @date 2017年5月3日  
 */
@Configuration
public class DBSourceConfiguration {

	@ConfigurationProperties(prefix = "spring.datasource.druid")
    @Bean(name="dataSource",initMethod = "init",destroyMethod = "close")
    @Primary
    public DruidDataSource getDataSource(){
    	DruidDataSource datasource = new DruidDataSource();
        return datasource;
    }

    @Bean(name = "transactionManager")
    @ConditionalOnMissingBean
    public PlatformTransactionManager transactionManager(){
        return new DataSourceTransactionManager(getDataSource());
    }
    
    @Bean
//    @Profile("dev")
    public FilterRegistrationBean druidStatFilter(){
       FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
       //添加过滤规则.
       filterRegistrationBean.addUrlPatterns("/*");
       //添加不需要忽略的格式信息.
       filterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
       return filterRegistrationBean;
    }
    
	@Bean
//	@Profile("dev")
    public ServletRegistrationBean DruidStatViewServle(){
		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
	       //添加初始化参数：initParams
	       //白名单：
	       servletRegistrationBean.addInitParameter("allow","127.0.0.1");
	       //IP黑名单 (存在共同时，deny优先于allow) : 如果满足deny的话提示:Sorry, you are not permitted to view this page.
	       servletRegistrationBean.addInitParameter("deny","192.168.1.73");
	       //登录查看信息的账号密码.
	       servletRegistrationBean.addInitParameter("loginUsername","admin");
	       servletRegistrationBean.addInitParameter("loginPassword","123456a?");
	       //是否能够重置数据.
	       servletRegistrationBean.addInitParameter("resetEnable","false");
	       return servletRegistrationBean;
	}
}
