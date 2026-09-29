package com.mnu.sample.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mnu.sample.service.BoardService;
import com.mnu.sample.service.NoticeService;
import com.mnu.sample.service.PdsService;

@Controller
@RequestMapping("")
public class IndexController {
	//로그 출력용
	private static final Logger log =
			LoggerFactory.getLogger(IndexController.class);
	
	//주입
	@Autowired
	private NoticeService noticeService;
	@Autowired
	private BoardService boardService;
	@Autowired
	private PdsService pdsService;
	
	@GetMapping("")
	public String getIndex(Model model) {
		log.info("Call : main index");
		
		model.addAttribute("nList", noticeService.noticeTopList(3));//최근 공지 3개
		model.addAttribute("bList", boardService.boardTopList(5));//최근 게시글 5개
		model.addAttribute("pList", pdsService.pdsTopList(3));//최근 자료실 3개
		
		return "index";
	}
}
