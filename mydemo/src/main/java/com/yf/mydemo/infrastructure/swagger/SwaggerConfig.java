package com.yf.mydemo.infrastructure.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.yf.mydemo.interfaces"))
                .paths(PathSelectors.any())
                .build().apiInfo(new ApiInfoBuilder()
                        .title("SpringBoot整合Swagger")
                        .description("详细信息")
                        .version("1.0")
                        .contact(new Contact(
                                "mtb",
                                "",
                                ""))
                        .license("The Apache License")
                        .licenseUrl("")
                        .build()
                ).securitySchemes(Collections.singletonList(new ApiKey("BASE_TOKEN","token","pass")))
                .securityContexts(Collections.singletonList(SecurityContext.builder().securityReferences(Collections.singletonList(
                        new SecurityReference("BASE_TOKEN",new AuthorizationScope[]{
                                new AuthorizationScope("global","")
                        })
                )).build()));
    }
}
