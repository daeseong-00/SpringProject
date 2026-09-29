package com.mnu.sample.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	//로그 출력용
	private static final Logger log =
			LoggerFactory.getLogger(UserController.class);

	//로그인 폼
	@GetMapping("Join/user_login")
	public String userLogin(HttpSession session) {
		log.info("User Call : user_login");
			return "/Join/user_login";
		}
	
	//로그인 에러
	@GetMapping("/Join/user_error")
	public String userLoginError(HttpSession session) {
		log.info("User Call : user_error");
			return "/Join/user_error";
		}
		
	//회원가입 폼
	@GetMapping("/Join/user_insert")
	public String userInsert() {
		log.info("User Call : userInsert");
		
		return "Join/user_insert";
	}
	
	//MyPage
	@GetMapping("/User/user_mypage")
	public String usermypage() {
		log.info("User Call : user_mypage");
		
		return "User/user_mypage";
	}
	
	

	


}