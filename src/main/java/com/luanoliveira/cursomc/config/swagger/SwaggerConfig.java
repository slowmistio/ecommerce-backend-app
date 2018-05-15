package com.luanoliveira.cursomc.config.swagger;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.luanoliveira.cursomc.resources.CategoriesResource;

import springfox.documentation.builders.AuthorizationCodeGrantBuilder;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.GrantType;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.service.TokenEndpoint;
import springfox.documentation.service.TokenRequestEndpoint;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@PropertySource("classpath:swagger.properties")
@ComponentScan(basePackageClasses = CategoriesResource.class)
@Configuration
public class SwaggerConfig {

	
	private static final String SWAGGER_API_VERSION = "1.0.0";
	private static final String TITLE = "REST Api Documentation";
	private static final String DESCRIPTION = "RESTFul Api Documentation by Curso MC";
	
	private static final String AUTH_SERVER = "";
	private static final String CLIENT_ID = "";
	private static final String CLIENT_SECRET = "";
	
	
	private ApiInfo apiInfo() {
		 return new ApiInfo(
				 TITLE, 
				 DESCRIPTION, 
				 SWAGGER_API_VERSION, 
		         "Terms of service", 
		         new Contact("Luan Oliveira", "www.luanoliveira.com", "luannn@gmail.com"), 
		         "License of API", "API license URL", Collections.emptyList());
	}
	
	@Bean
	public Docket clientApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("Client API")
				.select()	
				.apis(RequestHandlerSelectors.basePackage("com.luanoliveira.cursomc.resources"))
				.paths(PathSelectors.regex("/api/clients.*"))
				.build()
				.securitySchemes(Arrays.asList(securityScheme()))
				.apiInfo(apiInfo());
	}

	
	@Bean
	public Docket categoryApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("Category API")
				.select()	
				.apis(RequestHandlerSelectors.basePackage("com.luanoliveira.cursomc.resources"))
				.paths(PathSelectors.regex("/api/categories.*"))
				.build()
				.securitySchemes(Arrays.asList(securityScheme()))
				.apiInfo(apiInfo());
	}
	
	@Bean
	public SecurityConfiguration security() {
	    return SecurityConfigurationBuilder.builder()
	        .clientId(CLIENT_ID)
	        .clientSecret(CLIENT_SECRET)
	        .scopeSeparator(" ")
	        .useBasicAuthenticationWithAccessCodeGrant(true)
	        .build();
	}
	
	private SecurityScheme securityScheme() {
	    GrantType grantType = new AuthorizationCodeGrantBuilder()
	        .tokenEndpoint(new TokenEndpoint(AUTH_SERVER + "/token", "oauthtoken"))
	        .tokenRequestEndpoint(
	          new TokenRequestEndpoint(AUTH_SERVER + "/authorize", CLIENT_ID, CLIENT_ID))
	        .build();
	 
	    SecurityScheme oauth = new OAuthBuilder().name("spring_oauth")
	        .grantTypes(Arrays.asList(grantType))
	        .scopes(Arrays.asList(scopes()))
	        .build();
	    return oauth;
	}
	
	private AuthorizationScope[] scopes() {
	    AuthorizationScope[] scopes = { 
	      new AuthorizationScope("read", "for read operations"), 
	      new AuthorizationScope("write", "for write operations"), 
	      new AuthorizationScope("foo", "Access foo API") };
	    return scopes;
	}
	
}
