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
	//로그 출력용
	private static final Logger log =
			LoggerFactory.getLogger(AdminUserController.class);

	//회원 리스트
	@GetMapping("user_list")
	public String userList() {
		log.info("Admin User Call : user_list");
		
		return "Admin/user_list";
	}
	
	

}
