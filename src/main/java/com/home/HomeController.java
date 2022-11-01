package com.home;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping("/")
	public String home()throws Exception{
		
		return "index";
	}
}
