package com.home.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.home.member.MemberSecurityService;
import com.home.member.MemberSocialService;
import com.home.member.security.LoginFail;
import com.home.member.security.LoginSuccess;
import com.home.member.security.LogoutCustom;
import com.home.member.security.LogoutSuccessCustom;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private LoginSuccess loginSuccess;
	
	@Autowired
	private LoginFail loginFail;
	
	@Autowired
	private LogoutCustom logoutCustom;
	
	@Autowired
	private LogoutSuccessCustom logoutSuccessCustom;
	
	@Autowired
	private MemberSecurityService memberSecurityService;
	
	@Autowired
	private MemberSocialService memberSocialService;
	
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
	      //csrf token test
          .csrf()
          .disable()
          .cors()
          .configurationSource(this.configurationSource())
          .and()
//		//비허용 
//		.cors()
//		.disable()
//		.csrf()
//		.disable()
		
//		//cros 허용 
//		.csrf()
//		.disable()
//		.cors()
//		.and()
	.authorizeRequests()
		//.antMatchers("/", "/qna/list").permitAll() // 인덱스 페이지는 커밋 올. 누구나 접근 가능
		.antMatchers("/admin").hasRole("ADMIN") // 어드민 url은 admin role만 허용 
		.antMatchers("/manager").hasRole("MANAGER")
		.antMatchers("/qna/list").permitAll()
//		.antMatchers("/qna/**").hasRole("MEMBER")
		.anyRequest().permitAll()
		//.anyRequest().authenticated() //그 외는 role에 관계없이 로그인 한 사람만 허용
		.and()
	.formLogin() 
	.loginPage("/member/login") //내장된 로그인 폼 사용하지 않고 개발자가 만든 로그인폼으로 설정
	//.loginProcessingUrl("login") //로그인 진행 요청할 form 태그 action 주소 지정
	.passwordParameter("pw") //pw에 해당하는 파라미터 지정 
	.usernameParameter("id")
	//.defaultSuccessUrl("/") //인증 성공 시 리턴 경로
	.successHandler(loginSuccess) //성공했을 때 
	//.failureUrl("/member/login?error=true&message=LoginFail")
	.failureHandler(loginFail)
		.permitAll() 
		.and()  
	.logout() 
	.logoutUrl("/member/logout") 
//	.logoutUrl("/out") 
	.logoutSuccessUrl("/")
	  .logoutSuccessHandler(logoutSuccessCustom)
      .addLogoutHandler(logoutCustom)
      .invalidateHttpSession(true)   //세션을 지울거면 true 아니면 false
      .deleteCookies("JESSIONID")
		.permitAll()
		.and()
		.rememberMe() //자동 로그인
		.rememberMeParameter("rememberMe") //이 파라미터가 넘어오면 rememberMe 할거야.
		.tokenValiditySeconds(300) //로그인 유지 시간, 초단위, 세션 유지시간이 아님.
		.key("rememberMe") //인증받은 사용자의 정보로 Token 생성 시 필요, 필수값
		.userDetailsService(memberSecurityService) //인증 절차를 실행할 userDetailService. 
		.authenticationSuccessHandler(loginSuccess) //Login 성공 시 Handler
		.and()
	.oauth2Login() //socalLogin 설정
		.userInfoEndpoint()
		.userService(memberSocialService); 
		
			return httpSecurity.build();
	}
	
	//평문(Clear) 암호화 시켜주는 
	@Bean
	public PasswordEncoder getEncoder() {
		return new BCryptPasswordEncoder();
	} 
	
	//bean 등록하면 autowired 해서 가져오거나
	// 같은 클래스 안으 메소드라면 this. 로 가져올 수 잇음 
	//@Bean
	CorsConfigurationSource configurationSource () {
		CorsConfiguration configuration = new CorsConfiguration();
		
		configuration.setAllowedOrigins(Arrays.asList("http://127.0.0.1:5500", "*")); //asList(여기에 들어간 타입으로 제네릭을 만들어라.여러개넣을수있음.)
		//Arrays : 	배열에 관련된 메서드가 있는 클래스.
		// "*" = 어디서 접근하든 모두 허용 
		configuration.setAllowedMethods(Arrays.asList("GET", "POST")); //어떤 메소드 허용?
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		//사이트 내 어떤 url에 적용할 것이냐 
		//루트로 시작하는 것 모두 허용 
		return source;
	}

}
