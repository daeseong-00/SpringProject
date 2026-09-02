package com.mnu.sample.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("BoardPhoto")
public class BoardPhotoController {
	//로그 출력
		private static final Logger log =
				LoggerFactory.getLogger(BoardPhotoController.class);
		
		//포토게시판 리스트
		@GetMapping("list")
		public String boardPhotoList() {
			log.info("User Call : BoardList");
				
			return "BoardPhoto/board_list";
		}
	    //포토게시판 읽기
			@GetMapping("view")
			public String boardPhotoView() {
				log.info("User Call : boardPhotoView");
					
				return "BoardPhoto/board_view";
			}	
		
	}

