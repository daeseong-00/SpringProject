package com.mnu.sample.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("Admin/User")
public class AdminUserController {
	//로그 출력
	private static final Logger log =
			LoggerFactory.getLogger(AdminUserController.class);

	//게시판 리스트
	@GetMapping("user_list")
	public String userList() {
		log.info("Admin User Call : user_list");
		
		return "Admin/user_list";
	}
	//게시판 등록 처리
	@PostMapping("user_write")
	public String userWritePro() {
		log.info("Admin User Call : user_write");
			
		return "Admin/user_list";
	}
	//게시판 등록
	@GetMapping("user_view")
	public String userView() {
		log.info("Admin User Call : user_view");
			
		return "Admin/user_view";
	}		
		
}
