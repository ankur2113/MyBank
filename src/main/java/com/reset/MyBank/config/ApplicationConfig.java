package com.reset.MyBank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class ApplicationConfig {
	
	@Bean
	public Docket docketapi() {
		return new Docket(DocumentationType.SWAGGER_2)
					.apiInfo(mybankapiInfo())
					.select()
					.paths(PathSelectors.any())
					.build();
	}
	private ApiInfo mybankapiInfo() {
		return new ApiInfoBuilder()	
				.title("MyBank")
				.description("Spring Boot Bank Application")
				.version("1.0.0")
				.build();
				
	}

}
