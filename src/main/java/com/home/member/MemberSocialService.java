package com.home.member;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
		
		

		String social = userRequest.getClientRegistration().getRegistrationId();
		log.info("social => {}",social);
		//social별로 설정이 달라서 뽑아봄
		
		OAuth2User auth2User2= this.socialJoinCheck(userRequest);
		
		return auth2User2;
	}
	
	private OAuth2User socialJoinCheck(OAuth2UserRequest userRequest) {
		//회원가입 유무 체크 
		
		OAuth2User auth2User = super.loadUser(userRequest); //부모로 부터 가져오는 정보
		log.info("========== User info =============");
		log.info("Name => {}",auth2User.getName());
		log.info("Attributes() => {}",auth2User.getAttributes());
		log.info("Authorities => {}",auth2User.getAuthorities());
		
		Map<String, Object> map = auth2User.getAttributes();
		Iterator<String> keys = map.keySet().iterator();
		
		//Map에서 Key들을 꺼내기 iterator()사용하여 열거형으로꺼내기. 
		
		while(keys.hasNext()) {
			String key = keys.next();
			log.info("key => {}",key);
		}
		
		//필요한 정보들이  key 안의 properties에 들어있어서 한번 더 꺼내줘야 함. 
		
		Map<String, String> lm = auth2User.getAttribute("properties");
		Map<String, Object> ka = auth2User.getAttribute("kakao_account");
		
		//auth2User.getAttribute("properties").getClass().toString(); = 형변환하기위해  무슨 타입인가? 알아봐야함
		
		MemberVO memberVO = new MemberVO();
		memberVO.setId(auth2User.getName());
		memberVO.setName(lm.get("nickName"));
		memberVO.setEmail(ka.get("email").toString());
		
		memberVO.setSocial(userRequest.getClientRegistration().getRegistrationId());
		memberVO.setAttributes(auth2User.getAttributes());
		//Role 임의작성
		List<RoleVO> list = new ArrayList<>();
		RoleVO roleVO = new RoleVO();
		roleVO.setRoleName("ROLE_MEMBER");
		list.add(roleVO);
		
		memberVO.setRoleVOs(list);
		
		return memberVO;
	}
	
	
	

	
	

}
