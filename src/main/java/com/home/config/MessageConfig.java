package com.home.config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class MessageConfig implements WebMvcConfigurer{

	
	//***-context.xml
	//<bean class="" id=""> == new 생성자
	//@Bean("my") //bean 이름 설정
	//public Stirng getStarting(){
	//return new String();}
	
	@Bean
	public LocaleResolver locale() {
		//설정 파일
		
		// 둘 중 하나만 만들어서 리턴해주면 됨
		
		//1. sesseion
		SessionLocaleResolver resolver = new SessionLocaleResolver();
		resolver.setDefaultLocale(Locale.KOREAN);
		
		//2.Cookie 
		CookieLocaleResolver cResolver = new CookieLocaleResolver();
		//기본 위치 지정
		cResolver.setDefaultLocale(Locale.KOREAN);
		//이름 지정
		cResolver.setCookieName("lang");
		
		
		return cResolver;
		
	}
	
	//Interceptor 객체
	@Bean
	public LocaleChangeInterceptor changeInterceptor() {
		LocaleChangeInterceptor changeInterceptor = new LocaleChangeInterceptor();
		changeInterceptor.setParamName("lang");
		
		//parameter받아서 언어 구분
		//url?lang=en
		return changeInterceptor;
	}
	
	
	
}
