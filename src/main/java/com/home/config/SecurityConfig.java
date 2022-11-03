package com.home.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	

	@Bean
	//public을 선언하면 defalt로 바꾸라는 메시지 출력
	WebSecurityCustomizer webSecurityCustomizer() {
		//Security 에서 무시해야할 url pattern 등록 (보안과 상관 없는 것들 설정)
		return web -> web
				.ignoring() //무시하세요
				.antMatchers("/img/**")
				.antMatchers("/css/**")
				.antMatchers("/js/**")
				.antMatchers("/favicon/**")
				.antMatchers("/file/**");
		
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
		httpSecurity 
		.cors()
		.and()
		.csrf()
		.disable()
	.authorizeRequests()
		//.antMatchers("/", "/qna/list").permitAll() // 인덱스 페이지는 커밋 올. 누구나 접근 가능
		.antMatchers("/admin").hasRole("ADMIN") // 어드민 url은 admin role만 허용 
		.antMatchers("/manager").hasRole("MANAGER")
		.antMatchers("/qna/list").permitAll()
		.antMatchers("/qna/**").hasRole("MEMBER")
		.anyRequest().permitAll()
		//.anyRequest().authenticated() //그 외는 role에 관계없이 로그인 한 사람만 허용
		.and()
	.formLogin() 
	.loginPage("/member/login") //내장된 로그인 폼 사용하지 않고 개발자가 만든 로그인폼으로 설정
	//.loginProcessingUrl("login") //로그인 진행 요청할 form 태그 action 주소 지정
	.passwordParameter("pw") //pw에 해당하는 파라미터 지정 
	.usernameParameter("id")
	.defaultSuccessUrl("/") //인증 성공 시 리턴 경로
	.failureUrl("/member/login") //로그인 실패했을 경우 
		.permitAll() 
		.and()
	.logout()
	//.logoutUrl("/member/logout")
	.logoutUrl("/out")
	.logoutSuccessUrl("/")
	.invalidateHttpSession(true)
	.deleteCookies("JSESSIONID")
		.permitAll(); 
		
			return httpSecurity.build();
	}
	
	//평문(Clear) 암호화 시켜주는 
	@Bean
	public PasswordEncoder getEncoder() {
		return new BCryptPasswordEncoder();
	} 
	
	

}
