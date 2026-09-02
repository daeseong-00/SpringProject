package com.mnu.sample.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class IndexController {
	//로그 출력
	private static final Logger log =
			LoggerFactory.getLogger(IndexController.class);
	
	@GetMapping("")
	public String getIndex(Model model) {
		log.info("Call : main index");
		
		//model.addAttribute("nList", nlist); //최근 공지 3개
		return "index";
	}
	
}
