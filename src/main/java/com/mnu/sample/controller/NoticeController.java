package com.mnu.sample.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("Notice")
public class NoticeController {
	//로그 출력
		private static final Logger log =
				LoggerFactory.getLogger(NoticeController.class);
	
	//공지사항 리스트
	@GetMapping("list")
	public String noticeList() {
		log.info("User Call : noticeList");
			
		return "Notice/notice_list";
	}
	//공지사항 읽기
		@GetMapping("view")
		public String noticeView() {
			log.info("User Call : noticeView");
				
			return "Notice/notice_view";
		}	
	
}
