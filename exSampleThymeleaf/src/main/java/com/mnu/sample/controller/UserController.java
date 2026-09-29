package com.mnu.sample.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mnu.sample.domain.UserDTO;
import com.mnu.sample.service.EmailService;
import com.mnu.sample.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("User")
public class UserController {
	//로그 출력용
	private static final Logger log =
			LoggerFactory.getLogger(UserController.class);

	//UserService 주입
	@Autowired
	private UserService userService;
	
	//EmailService 주입
	@Autowired
	private EmailService emailService;
	
	
	//로그인 폼
	@GetMapping("user_login")
	public String userLogin(HttpSession session) {
		log.info("User Call : user_login");
		if(session.getAttribute("user") == null) {
			return "User/user_login";//로그인 페이지로 이동
		}else {
			//로그인한 사용자 일경우
			return "redirect:/";//컨트로러
		}
	}
	
	//로그인처리
	@PostMapping("user_login")
	public String userLoginPro(UserDTO userDTO, HttpServletRequest request) {
		log.info("User Call : user_login_ok");
		
		UserDTO uDTO = userService.userLogin(userDTO);
		
		if(uDTO != null) {//로그인 성공시
			//최근 로그인 날자 업데이트
			userService.userLastTimeUpdate(uDTO.getUserid());
			//세션 설정
			request.getSession().setAttribute("user", uDTO);
			request.getSession().setMaxInactiveInterval(10*60);//10분
		}
		
		return "User/user_login_ok";//경고창
	}
	//로그아웃 처리
	@GetMapping("user_logout")
	public String userLogout(HttpSession session) {
		log.info("User Call : logout");
		session.invalidate();
		
		return "redirect:/";//index로 이동
	}
		
	//회원가입 폼
	@GetMapping("user_insert")
	public String userInsert() {
		log.info("User Call : userInsert");
		
		return "User/user_insert";
	}
	
	
	
	//회원가입처리
	@PostMapping("user_insert")
	public String userInsertPro(UserDTO userDTO, Model model) {
		log.info("User Call : user_insert_pro");
		
		model.addAttribute("row", userService.userWrite(userDTO));
		return "User/user_insert_pro";//가입유무 출력 후 로그인 페이지로 이동하는 JSP
	}
	
	//정보수정 폼
	@GetMapping("user_modify")
	public String userModify() {
		log.info("User Call : userModify");
		
		return "User/user_modify";
	}

	//정보수정 처리
	
	//회원탈퇴(삭제)
	
	//ID찾기 폼
	
	
	//비번분실시 id 입력 폼
	
	
	//비번분실시 id를 찾어서 임시비번 발송

}