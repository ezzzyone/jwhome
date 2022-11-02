package com.home.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.extern.slf4j.Slf4j;

//설정파일만드는 객체 ***-context.xml 파일
@Configuration
@Slf4j
public class WebConfig implements WebMvcConfigurer{
	@Value("${app.local.path}")   //srEl                            
	private String filePath;
	@Value("${app.url.path}")
	private String urlPath;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry){
		
		log.info(filePath);
		registry.addResourceHandler(urlPath).addResourceLocations(filePath);
		//registry 등록해라. 
	}
}