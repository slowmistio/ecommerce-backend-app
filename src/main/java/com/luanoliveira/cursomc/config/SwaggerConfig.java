package com.luanoliveira.cursomc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.luanoliveira.cursomc.resources.CategoriesResource;

import io.swagger.models.Contact;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@PropertySource("classpath:swagger.properties")
@ComponentScan(basePackageClasses = CategoriesResource.class)
@Configuration
public class SwaggerConfig {

	
	private static final String SWAGGER_API_VERSION = "1.0";
	private static final String LICENSE_TEXT = "License";
	private static final String title = "CursoMC REST API";
	private static final String descripton = "RESTFul API For CursoMC";
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title(title)
				.description(descripton)
				.license(LICENSE_TEXT)
				.version(SWAGGER_API_VERSION)
				.build();
	}
	
	@Bean
	public Docket categoriesApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()	
				.apis(RequestHandlerSelectors.basePackage("com.luanoliveira.cursomc"))
				.paths(PathSelectors.regex("/api.*"))
				.build()
				.apiInfo(apiInfo());
	}
	
}
