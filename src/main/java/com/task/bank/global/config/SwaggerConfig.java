package com.task.bank.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	// http://localhost:8080/swagger-ui/index.html

    @Bean
    public Docket api() {
    	 return new Docket(DocumentationType.SWAGGER_2)
                 .useDefaultResponseMessages(false)
                 .select()
                 .apis(RequestHandlerSelectors.any())
                 .paths(PathSelectors.any())
                 .build()
                 .tags(
                     new Tag("1. 계약 생성 API", "1"),
                     new Tag("2. 계약 정보 조회 API", "2"),
                     new Tag("3. 계약 정보 수정 API", "3"),
                     new Tag("4. 예상 총 보험료 계산 API", "4")
                 )
                 .apiInfo(metaData())
                 ;
    }

    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("계약관리시스템 API")
                .description("보험사의 계약관리 API 시스템")
                .build();
    }

}
