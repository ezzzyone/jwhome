package com.home.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class MemberService {
	
	@Autowired
	private MemberMapper memberMapper;
	
	
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
