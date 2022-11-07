package com.home.member;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/member/*")
public class MemberController {
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
		@GetMapping("login")
		public void login(@RequestParam(defaultValue = "false", required = false)boolean error, String message, Model model ){
		
			if(error) {
				model.addAttribute("msg","로그인 오류입니다.");
			}
		}
		
		@GetMapping("join")
		public void setJoin(@ModelAttribute MemberVO memberVO){
			
		}
		
		@PostMapping("join")
		public ModelAndView setAdd(@Valid MemberVO memberVO, BindingResult bindingResult)throws Exception{
			//@Valid = 검증해라 그리고 BindingResult 에 담아라 . 순서 지켜서 작성
			ModelAndView mv = new ModelAndView();
			
			boolean check = memberService.getMemberError(memberVO, bindingResult);
		      if(check) {
		         log.info("==========검증 에러 발생==========");
		         mv.setViewName("member/join");
		         
		         //=======================================
			      List<FieldError> error = bindingResult.getFieldErrors();
			     for(FieldError fieldError : error) {
			    	 log.info("+++++++++++++++++++++++++++++++++");
			    	 log.info("fieldError= "+fieldError);
			    	 log.info("getField= "+fieldError.getField());
			    	 log.info("RejectedValue= "+fieldError.getRejectedValue());
			    	 log.info("DefaultMessage= "+fieldError.getDefaultMessage());
			    	 log.info("getCode= "+fieldError.getCode());
			    	 
			    	 mv.addObject(fieldError.getField(),fieldError.getDefaultMessage());
			    	 
			     }
		         return mv;
		      } else {
		    	memberVO.setPw(passwordEncoder.encode(memberVO.getPw()));
		  		int result = memberMapper.setMember(memberVO);
		  		memberVO.setNum(1);
		  		result = memberMapper.setRole(memberVO);
				if (result>0) {
					mv.addObject("msg", "조인 성공");
					mv.addObject("url","/");
				
				}else {
					mv.addObject("msg", "조인 실패");
					mv.addObject("url", "member/join");
					throw new Exception();
				}
				mv.setViewName("/alert");
		      }
				
				return mv;
			
		}

}
