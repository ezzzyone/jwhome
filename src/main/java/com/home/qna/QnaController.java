package com.home.qna;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.home.util.Pager;

import lombok.extern.slf4j.Slf4j;

import com.home.qna.QnaVO;

@Controller
@Slf4j
@RequestMapping("/qna/*")
public class QnaController {
	
	@Autowired
	private QnaMapper qnaMapper;
	
	@Autowired
	private QnaService qnaService;
	
	@GetMapping("list")
	public ModelAndView list(Pager pager)throws Exception{
		ModelAndView mv = new ModelAndView();
		pager.getRowNum();
		System.out.println("StartRow"+pager.getStartRow());
		System.out.println("perpage"+pager.getPerPage());
		Long c = qnaMapper.getListCount(pager); //카운트가 안뽑힘
		System.out.println("getListCount"+c);
		pager.getNum(qnaMapper.getListCount(pager));
		List<QnaVO> ar = qnaMapper.getList(pager);
		
		mv.addObject("vo", ar);
		mv.setViewName("board/list");
		
		return mv;
	}
	
	@GetMapping("write")
	public String write(@ModelAttribute QnaVO qnaVO)throws Exception{
		return "board/write";
		
	}
	
	@PostMapping("write")
	public ModelAndView write(@Valid QnaVO qnaVO,BindingResult bindingResult ,RedirectAttributes redirectAttributes)throws Exception{

		ModelAndView mv = new ModelAndView();
		boolean check = qnaService.getQnaError(qnaVO, bindingResult);
		   if(check) {
		         log.info("==========검증 에러 발생==========");
		        
			      List<FieldError> error = bindingResult.getFieldErrors();
			      
			     for(FieldError fieldError : error) {
			    	 log.info("+++++++++++++++++++++++++++++++++");
			    	 log.info("fieldError= "+fieldError);
			    	 log.info("getField= "+fieldError.getField());
			    	 log.info("RejectedValue= "+fieldError.getRejectedValue());
			    	 log.info("DefaultMessage= "+fieldError.getDefaultMessage());
			    	 log.info("getCode= "+fieldError.getCode());
			    	 
			    	 mv.setViewName("board/write");
			    	 mv.addObject(fieldError.getField(),fieldError.getDefaultMessage());
			    	 
			     }
		         return mv;
		      }else {
		    	  int result = qnaService.setList(qnaVO);
		    	  mv.setViewName("board/list");
					return mv;
		      }

	}
	

	
}
