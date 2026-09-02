package com.mnu.sample.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mnu.sample.service.BoardService;

@Controller
@RequestMapping("Board")
public class BoardController {
	//로그 출력
		private static final Logger log =
				LoggerFactory.getLogger(BoardController.class);
	
		@Autowired
		private BoardService boardService;
		
	//자유게시판 리스트(검색 x, 페이징처리 x)
		@GetMapping("list")
		public String boardList(Model model) {
			log.info("Board Call : BoardList");
			model.addAttribute("totcount", boardService.boardCount());
			model.addAttribute("bList", boardService.boardList());
			
			return "Board/board_list";
		}
	//자유게시판 리스트(검색 o, 페이징처리 x)
		@PostMapping("list")
		public String boardListSearch(String search, String key, Model model) {
			log.info("Board Call : BoardList");
			model.addAttribute("totcount", boardService.boardCountSearch(search, key));
			model.addAttribute("bList", boardService.boardListSearch(search, key));
			model.addAttribute("search", search);
			model.addAttribute("key", key);
			return "Board/board_list";
		}
/*		
		@RequestMapping(value="list", method= {RequestMethod.GET, RequestMethod.POST})
		public String boardListSearch(String search, String key) {
			
			return "";
		}
*/		
		//자유게시판 읽기
		@GetMapping("view")
		public String boardView() {
			log.info("Board Call : boardView");
					
			return "Board/board_view";
		}	
		
	}
		
		

