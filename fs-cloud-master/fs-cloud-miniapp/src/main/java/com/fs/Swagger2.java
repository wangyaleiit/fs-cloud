package com.fs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/***
 * 
 * @ClassName Swagger2
 * @Description TODO
 * @author wang.yalei@fujisoft-china.com
 * @date 2017年11月22日
 */
@Configuration
@EnableSwagger2
public class Swagger2 {

	@Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.fs.web"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("fs_cloud 中使用Swagger2构建RESTful APIs")
                .description("api根地址：http://localhost/:8080")
                .termsOfServiceUrl("http://wangyalei.iteye.com")
                .contact(new Contact("王亚磊","http://wangyalei.iteye.com","wang.yalei@fujisoft-china.com"))
                .version("1.0")
                .build();
    }
}
