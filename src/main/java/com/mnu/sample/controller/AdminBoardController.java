package com.mnu.sample.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("Admin/Board")
public class AdminBoardController {
	//로그 출력
	private static final Logger log =
			LoggerFactory.getLogger(AdminBoardController.class);

	//게시판 리스트
	@GetMapping("board_list")
	public String boardList() {
		log.info("Admin Board Call : board_list");
		
		return "Admin/board_list";
	}
	//게시판 등록 폼
	@GetMapping("board_write")
	public String boardWrite() {
		log.info("Admin Board Call : board_write");
			
		return "Admin/board_write";
	}
	//게시판 등록 처리
	@PostMapping("board_write")
	public String boardWritePro() {
		log.info("Admin Board Call : board_write");
			
		return "Admin/board_list";
	}
	//게시판 등록
	@GetMapping("board_view")
	public String boardView() {
		log.info("Admin Board Call : board_view");
			
		return "Admin/board_view";
	}		
		
}
