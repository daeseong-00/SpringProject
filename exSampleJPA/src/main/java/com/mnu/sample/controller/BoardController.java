package com.mnu.sample.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mnu.sample.dto.BoardRequestDTO;
import com.mnu.sample.service.BoardService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("Board")
public class BoardController {
	//로그 출력용
	private static final Logger log =
			LoggerFactory.getLogger(BoardController.class);
	
	private final BoardService boardService;
	
	@GetMapping("board_list")
	public String boardList(Model model) {
		log.info("Board Call : board_list");
		model.addAttribute("bList", boardService.boardList());
		return "Board/board_list";
	}
	
	//등록폼
	@GetMapping("board_write")
	public String boardWrite() {
		log.info("Board Call : board_write");
		return "Board/board_write";
	}
	
	//등록처리
	@PostMapping("board_write")
	public String boardWritePro(BoardRequestDTO board) {
		log.info("Board Call : board_write_pro");
		int row = boardService.boardWrite(board);
		if(row==0) {
			return "Board/board_write";
		}else {
			return "redirect:board_list";
		}
		
	}

}
