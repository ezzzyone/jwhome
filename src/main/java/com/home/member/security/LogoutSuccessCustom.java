package com.home.member.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.home.member.MemberVO;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LogoutSuccessCustom implements LogoutSuccessHandler{
	
	@Value("${spring.security.oauth2.client.registration.kakao.client-id}")
	private String client_id;
	
	@Value("${kakao.logout-redirect-uri}")
	private String uri;

	@Override
		public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
				throws IOException, ServletException {
		log.info("logout 성공 시 실행");
		
		MemberVO memberVO = (MemberVO)authentication.getPrincipal();
		String social = memberVO.getSocial();
		
		if(social != null) {
			if(social.equals("kakao")) {

//					response.sendRedirect("https://developers.kakao.com/logout");
//					//response.sendRedirect("https://kauth.kakao.com/oauth/logout?client_id="+client_id+"&logout_redirect_uri="+uri);

			RestTemplate restTemplate = new RestTemplate();
				
				//header X
				//parameter X
			
//			MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
//			HttpEntity<MultiValueMap<String, String>> req = new HttpEntity<>(map);
			
			ResponseEntity<String> res	= restTemplate.getForEntity("https://developers.kakao.com/logout",String.class);
			log.info("res => {}", res);
			response.sendRedirect("/");
			
			}else if(social.equals("naver")) {
				
			}else {
				
			}
		}else {
			response.sendRedirect("/");
		}
		
			
		}

	
}
