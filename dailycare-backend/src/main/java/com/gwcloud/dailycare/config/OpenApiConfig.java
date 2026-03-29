package com.gwcloud.dailycare.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {
 @Bean
 public OpenAPI dailyCareOpenAPI() {
	 	return new OpenAPI()
	 			.info(new Info()
	 			.title("DailyCare API")
	 			.description("개체, 착유, 사료, 번식 기록을 관리하는 API문서")
	 			.version("V1"));
	 
 }
} 
