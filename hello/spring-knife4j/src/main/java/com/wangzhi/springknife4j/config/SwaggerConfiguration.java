package com.wangzhi.springknife4j.config;


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

/**
 * @author : wz157
 * @date : 2020-05-24 19:17
 * @description : swagger-ui2的配置文件
 * @path : com.wangzhi.springknife4j.config.SwaggerConfiguration
 * @modifiedBy : wz157
 * @modifyDate : 2020-05-24 19:17
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.wangzhi.springknife4j"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * http://${host}:${port}/doc.html
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("swagger-bootstrap-ui RESTful APIs")
                .description("swagger-bootstrap-ui")
                .termsOfServiceUrl("http://localhost:8999/")
                .contact(new Contact("王智", "www.baidu.com", "15713598138@sina.cn"))
                .version("1.0")
                .build();
    }
}
