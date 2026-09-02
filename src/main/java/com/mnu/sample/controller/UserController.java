package com.mnu.sample.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("User")
public class UserController {
	//로그 출력
	private static final Logger log =
			LoggerFactory.getLogger(UserController.class);
	
	//로그인 폼
	@GetMapping("login")
	public String userLogin() {
		log.info("User Call : login");
		
		return "User/user_login";
	}
	//로그아웃 처리
	@GetMapping("logout")
	public String userLogout() {
		log.info("User Call : logout");
		
		return "redirect:";
	}
	//회원가입
	@GetMapping("insert")
	public String userInsert() {
		log.info("User Call : userInsert");
		
		return "User/user_insert";
	}
	//정보수정
		
		
}
