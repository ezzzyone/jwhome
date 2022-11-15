package com.home;

import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.function.ServerRequest.Headers;

import com.home.qna.PostVO;
import com.home.util.TestInterface;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@Slf4j
public class HomeController {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping("/arrow")
	public void arrow() {
		//lamda 식 (Js Arrow funtion)
		TestInterface t = (m)->System.out.println(m);
			t.info("test");
	
	}
	
	@GetMapping("/web")
	public String webClientTest() {
		//Calendar ca = Calendar.get
//		WebClient webClient =  WebClient.create();
//		webClient = WebClient.create("http://localhost:81");//baseUri = 변하지 않는 url
		
		//추가요청발생시키기 
		WebClient webClient = WebClient.builder()
				 .baseUrl("https://jsonplaceholder.typicode.com")
//				 .defaultHeader("Key", "value")
//				 .defaultCookie("key", "value")
				 .build();
		//Mono = 결과물이 하나일 때. 
//		Mono<PostVO> res = webClient.get()
//											.uri("/posts/2")
//											.retrieve()
//											.bodyToMono(PostVO.class);
//		PostVO postVO = res.block();
//		log.info("Mono => {}", postVO);
		
		//Flux = postVO 가 여러개 일 때
		Flux<PostVO> res = webClient.get()
				.uri("/posts")
				.retrieve()
				.bodyToFlux(PostVO.class);
				
		PostVO postVO = res.blockFirst();
		
		res.subscribe((s)->{
			PostVO pvo = s; //s 변수가 같은 PostVO타입이면 에러 없이 잘 담김.
			log.info("id => {}", s.getUserId());
		});
		
		//익명함수처럼 선언해서 사용할 수 있음 
		//(매개변수)->{}
		//res = flux 여러개
		//subscribe = For 같은 함수
		
		
//		//map으로 key,value 보내기
//		Map<String, Object> map = new HashMap<>();
//		map.put("page", 1);
//		map.put("kind", "title");
//		//uri("/list", map)
//		
//		//vo로 보내기
//		PostVO postVO = new PostVO();
//		postVO.setUserId("1");
//		//uri("/list", postVO)
//	 
//		webClient.get()
//				.uri("/posts/1")
//				.header("k", "v")
//				.cookie("k", "v");
//		
		
		return "";
	}
	
	@GetMapping("/address")
	@ResponseBody
	public String address()throws Exception{
		//kakao 지도 요청 
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "KakaoAK e63a369245274893a79b0baace53f1b8"); //key들은 value annotation 쓰는 것이 좋음
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("query", "한남더힐");
		
		HttpEntity<MultiValueMap<String, String>> req = new HttpEntity<MultiValueMap<String, String>>(params, headers);
		ResponseEntity<String> res = restTemplate.getForEntity("https://dapi.kakao.com/v2/local/search/address.json",String.class, req);
		
		return res.getBody();
	}
	
	@GetMapping("/")
	public String home(HttpSession session)throws Exception{
		
		RestTemplate restTemplate = new RestTemplate();
		
		
		//헤더 생성 
		HttpHeaders headers = new HttpHeaders();
		//header = key:value
		//headers.add("key", "value");
		//headers.set헤더명("값");
		//headers.addAll(headers); //MultiValueMap<K, V> 타
		
		
		//파라미터 설정 
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("key", "value");
		
		//url , method 를 포함한 요청 객체 생성 (헤더 + 파라미터) 
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String,String>>(params, headers);
		
		//전송 후 결과 
		List<PostVO> posts = restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts",List.class, request);
		log.info("posts -> {}", posts);

		
		SecurityContextImpl context = (SecurityContextImpl)session.getAttribute("SPRING_SECURITY_CONTEXT");
		if(context != null) {
			log.info("Context => {}",context);
		//log.info("Context => {}", ((MemberVO)context.getAuthentication().getPrincipal()).getId());
		//정확히 말하면 MemberVO 아니고 UserDeatail 타입 
		}
		//System.out.println(message);
//		log.error("Error message");
//		log.warn("warn message");
//		log.info("info message");
//		log.debug("debug message");
//		log.trace("trace message");
		
		//List<QnaVO> ar = qnaMapper.getList();
		//log.info("List : {} size {}", ar, ar.size());
		
		return "index";
	}
		
}
