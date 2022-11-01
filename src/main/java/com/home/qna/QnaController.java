package com.home.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.home.util.Pager;

@Controller
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

	
}
