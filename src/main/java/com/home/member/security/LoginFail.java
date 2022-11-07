package com.home.member.security;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LoginFail implements AuthenticationFailureHandler {
@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		// TODO Auto-generated method stub
	
	log.info("==================LOGIN FAIL===================");
	log.info("exception => {}",exception);
	log.info("exception class => {}",exception.getClass().toString());
	log.info("Local Mes => {}",exception.getLocalizedMessage());
	log.info("Cause => {}",exception.getCause());
	log.info("mes => {}",exception.getMessage());
	
	String name = exception.getClass().toString();
//	name = name.substring(name.lastIndexOf("."))+1; //. 포함되어 출력되기 때문에 
//	if(name) {
//	}
	
//	name = name.substring(name.lastIndexOf(".")); //. 포함되어 출력되기 때문에 
//	if(name.equals(".BadCredentialsException")) {
//		name="비밀번호를 확인해주세요.";
//	}
	String rs = null;
	if(exception instanceof BadCredentialsException) {
		rs ="비밀번호를 확인해주세요.";
	}else if(exception instanceof InternalAuthenticationServiceException) {
		rs ="아이디를 확인해주세요 ";
	}else {rs ="로그인실ㅠㅐ ";}
		
	//리다이렉트하기 - 인코딩 안해주면 에러남 
	rs = URLEncoder.encode(rs,"UTF-8");
	response.sendRedirect("/member/login?error=true&message="+rs);
	
	request.setAttribute("msg", rs);
	
//	//forward로 jsp를 바로 보냄 
//	request.getRequestDispatcher("/WEB-INf/views/member/login.jsp").forward(request, response); 
//	
//	//포워드해서 컨트롤러 메소드로 보내기 
//	request.getRequestDispatcher("/member/login").forward(request, response);
		
	}
}
