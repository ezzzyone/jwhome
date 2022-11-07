package com.home.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.stereotype.Service;


import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MemberSecurityService implements UserDetailsService{
	//소셜 로그인 + security 같이 하는 경우
	// public class MemberSecurityService extends DefaultOAuth2UserService implements UserDetailsService{
	//  extends DefaultOAuth2UserService 
	// implements UserDetailsService
	@Autowired //IOC
	private MemberMapper memberMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("===========로그인 시도 중  ==============");
		//여기에서는 로그인 확인불가 아이디 꺼내기만함
		//스프링 필터에서 패스워드 비교하여야 로그인 확인 가능함
		MemberVO memberVO = new MemberVO();
			memberVO = memberMapper.getLogin(username);
		return memberVO;
	}
}
