package com.fs.config;

import javax.annotation.Resource;
import javax.sql.DataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

/***
 * ORM配置
* @ClassName: MybatisConfiguration  
* @Description: TODO(这里用一句话描述这个类的作用)  
* @author 王亚磊   
* @date 2017年5月3日  
*
 */
@Configuration
@AutoConfigureAfter({DBSourceConfiguration.class})
@MapperScan(basePackages={"com.fs.mapper.**.mapper"})
public class MybatisConfiguration implements EnvironmentAware{

	private static Log logger = LogFactory.getLog(MybatisConfiguration.class);  
	
	private RelaxedPropertyResolver propertyResolver; 
	
	@Resource(name="dataSource")  
	private DataSource dataSource;
	
	@Override
	public void setEnvironment(Environment environment) {
		 this.propertyResolver = new RelaxedPropertyResolver(environment,"mybatis.");
	}
	
    @Bean(name ="sqlSessionFactory")  
    @ConditionalOnMissingBean
	public SqlSessionFactory sqlSessionFactry() {
    	try {
    		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
    		sessionFactory.setDataSource(dataSource);
        	sessionFactory.setTypeAliasesPackage(propertyResolver.getProperty("typeAliasesPackage"));
        	PathMatchingResourcePatternResolver pathMatchingResource= new PathMatchingResourcePatternResolver();        
			sessionFactory.setMapperLocations(pathMatchingResource.getResources(propertyResolver.getProperty("mapperLocations")));
			sessionFactory.setConfigLocation(new DefaultResourceLoader().getResource(propertyResolver.getProperty("config-location")));  
	        return sessionFactory.getObject();
    	} catch (Exception e) {
    		logger.warn("Could not confiure mybatis session factory");  
	        return null;
		}
	}
    
    @Bean(name = "sqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
