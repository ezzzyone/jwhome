package com.home.member;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.util.StringBuilderFormattable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class MemberService {
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Value("${social.admin.key}") //spring ramework 어노테이션 써줌. Lombok 이랑 관계 없음. 
	private String adminKey;
	
	public int setDelete2(MemberVO memberVO) throws Exception{
		//WebClient 생성 
		WebClient webClient = WebClient.builder()
										.baseUrl("https://kapi.kakao.com/")
										.build();
		
		//paramter
		//VO타입이없다면 key:value 형식인 Map 사용
		
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("target_id_type", "user_id");
		map.add("target_id", memberVO.getId());
		
		Mono<String> res = webClient.post()
				.uri("v1/user/unlink")
				.body(BodyInserters.fromFormData(map))
				.header("Authorization", "KakaoAK "+adminKey)
				.header("Content-Type", "application/x-www-form-urlencoded")
				.retrieve()
				.bodyToMono(String.class);

		log.info("WebClient -> {}", res.block());
		
		return 1;
	}
	
	
		public int setDelete(MemberVO memberVO) throws Exception{
			int result = 0;
			//소셜로그인 인지 일반로그인 인지 분기 처리
			//kakao deviloper rest api 연결끊기 부분
			RestTemplate restTemplate = new RestTemplate();
			
			//Header
			//헤더만드는 방법도 여러가지 
			HttpHeaders headers = new HttpHeaders();
			//add로 추가하는 방법, set으로타입을 찾는 방법 
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED); //=application/x-www-form-urlencoded" 
			headers.add("Authorization", "KakaoAK "+adminKey);
			
			//param
			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
			params.add("target_id_type", "user_id");
			params.add("target_id", memberVO.getId());
			
			//요청 객체
			HttpEntity<MultiValueMap<String, String>> req = new HttpEntity<>(params,headers);
		
			
			//전송 후 결과 처리
			//ResponseEntity<String> 타입은 매개변수 부분 class<T> 타입
			ResponseEntity<String> res = restTemplate.postForEntity("https://kapi.kakao.com/v1/user/unlink", req, String.class);
			//res는 string 타입이 아니어서 res.getBody()로 불러옴 
			log.info("!!!!!!! res => {}", res.getBody());
			
			if(res.getBody() != null) {
				result =1;
			}
			return result;
			
		}
	
	
	   //사용자 정의 검증 메서드
	   public boolean getMemberError(MemberVO memberVO, BindingResult bindingResult) throws Exception{
	      
	      boolean check = false;
	      //check = false : 검증성공(error 없음)
	      //check = true : 검증실패(error 있음)
	      
	      //1. Annotation검증
	      check = bindingResult.hasErrors();
	      
	      //2. password가 일치하는지 검증
	      if(!memberVO.getPw().equals(memberVO.getPw2())) {      // pw와 pwCheck가 같지 않으면 true로 에러발생
	         check = true;
	         //에러메시지
//	         bindingResult.rejectValue("멤버변수명(path)", "properties의 key(코드)");
	         bindingResult.rejectValue("pw2", "member.password.notEqual");
	      }
	      
	      //3. Id가 중복인지 검증
	      
	      int result = memberMapper.getIdCheck(memberVO);
	      if(result>0) {
	    	  check=true;
	    	  bindingResult.rejectValue("id","member.id.equal");
	      } 
	     
	      
	      return check;
	   }

}
