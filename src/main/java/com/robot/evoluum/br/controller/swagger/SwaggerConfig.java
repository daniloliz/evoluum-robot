package com.robot.evoluum.br.controller.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebFlux;

@Configuration
@EnableSwagger2WebFlux
public class SwaggerConfig {

    @Bean
    public Docket docketApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.robot.evoluum.br.controller.endpoint"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(descriptionsApis());
    }

    public ApiInfo descriptionsApis() {
        return new ApiInfoBuilder()
                .title("Robot Evoluum API")
                .description("REST APIs moved the robot - NASA")
                .version("1.0.0")
                .build();
    }
}
