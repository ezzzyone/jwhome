package com.home.member;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

//kakao social login - boot
@Service
@Slf4j
public class MemberSocialService extends DefaultOAuth2UserService{
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		log.info("================social login================");
		//userRequest = 소셜 로그인 정보 들어있음.
		log.info("userRequest => {}",userRequest);
		log.info("getAccessToken() => {}",userRequest.getAccessToken());
		log.info("getClientRegistration() => {}",userRequest.getClientRegistration());
		
	OAuth2User auth2User = super.loadUser(userRequest); //부모로 부터 가져오는 정보
		log.info("========== User info =============");
		log.info("Name => {}",auth2User.getName());
		log.info("Attributes() => {}",auth2User.getAttributes());
		log.info("Authorities => {}",auth2User.getAuthorities());
		
		
		return super.loadUser(userRequest);
	}

}
