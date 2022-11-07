package com.home.member.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LoginSuccess implements AuthenticationSuccessHandler {
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// 성공했을 때 실행되는 추가작업 
		log.info("==============LOGIN SUCCESS===============");
		log.info("Aut => {}",authentication); //매개변수로 받은 authentication에 로그인 정보 들어있음
		log.info("ID : {}",request.getParameter("id"));
		Cookie cookie = new Cookie("userId", request.getParameter("id"));
		String check = request.getParameter("rememberId");
		log.info("check : {}",check);
		if(check != null && check.equals("on")) { //체크박스 체크하면 쿠키 만들기 
			cookie.setMaxAge(60); //초 단위 .쿠키 살아있는 시간
			cookie.setHttpOnly(true);
			cookie.setPath("/");
			response.addCookie(cookie); //응답으로 쿠키를 보내줘야함 
			
		}
		
		else {
	         Cookie[] cookies = request.getCookies();
	         for(Cookie ck : cookies) {
	            if(ck.getName().equals("userId")) {
	               ck.setMaxAge(0);
	               ck.setPath("/");               
	               response.addCookie(ck);
	            }
	         }
	         
	      }
		
		
		response.sendRedirect("/");
		
		
	}

}
