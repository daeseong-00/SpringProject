package com.mnu.sample.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class IndexController {
	//로그 출력용
	private static final Logger log =
			LoggerFactory.getLogger(IndexController.class);
	
	public String getIndex(Model model) {
		log.info("Call : main Index");
		//공지사항 최근 5개
		
		//게시판 최근 5개
		
		return "index";
	}
	
}
