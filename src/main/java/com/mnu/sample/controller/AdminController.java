package com.mnu.sample.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("Admin")
public class AdminController {
	//로그 출력
	private static final Logger log =
			LoggerFactory.getLogger(AdminController.class);
	
	//로그인 폼
	@GetMapping("login")
	public String adminLogin() {
		log.info("Admin Call : login");
		
		return "Admin/admin_login";
	}
	//관리자 목록
	@GetMapping("admin_list")
	public String adminList() {
		log.info("Admin Call : admin_list");
		
		return "Admin/admin_list";
	}
	//로그아웃 처리
	@GetMapping("logout")
	public String AdminLogout() {
		log.info("Admin Call : logout");
		
		return "redirect:";
	}
	//회원가입
	@GetMapping("insert")
	public String AdminInsert() {
		log.info("Admin Call : adminInsert");
		
		return "Admin/admin_insert";
	}
	//정보수정
		
		
}
