package com.mnu.sample.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mnu.sample.dto.UserRequestDTO;
import com.mnu.sample.exception.CustomException;
import com.mnu.sample.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("User")
public class UserController {
	//로그 출력용
	private static final Logger log =
			LoggerFactory.getLogger(BoardController.class);
	
	private final UserService userService;
	
	//회원가입 폼
	@GetMapping("user_insert")
	public String userInsert(Model model) {
		log.info("User Call : user_insert");
		model.addAttribute("userRequestDTO", new UserRequestDTO());
		return "User/user_insert";
	}
	
	//ID 중복검사
	@ResponseBody
	@PostMapping("user_idCheck")
	public String userIdCheck(@RequestParam("userid") String userid) {
		boolean bool = userService.userIdCheck2(userid);
		return String.valueOf(bool);
	}
	
	//본인인증(핸드폰)
	
	
	//본인인증(이메일)
	
	
	//회원가입처리
	@PostMapping("user_insert")
	public String userInsertPro(@Valid UserRequestDTO userRequestDTO, BindingResult result, Model model) {
		log.info("User Call : user_insert_pro");
		
		if(result.hasErrors()) {
			return "User/user_insert";
		}
		
		return "/";
	}
	
	//로그인 폼
	@GetMapping("user_login")
	public String userLogin() {
		log.info("User Call : user_login");
		return "User/user_login";
	}
	
	//로그인 처리
	@PostMapping("user_login")
	public String userLoginPro(UserRequestDTO userRequestDTO, HttpServletRequest request){
		log.info("User Call : user_login_pro");
		try {
			request.getSession().setAttribute("user",userService.userLogin(userRequestDTO));
			request.getSession().setMaxInactiveInterval(1800);//세센유효시간
			return "redirect:/";
		}catch(CustomException e) {
	         return "redirect:user_login"; // 다시 로그인 페이지
			 
		}		
	}
	
	//마이페이지
	@GetMapping("user_mypage")
	public String userMyPage() {
		log.info("User Call : user_mypage");
		return "User/user_mypage";
	}
	
	
	//로그아웃 처리
	@GetMapping("user_logout")
	public String userLogout(HttpSession session) {
		log.info("User Call : user_logout");
		session.invalidate();
		
		return "redirect: /";
	}
	
	
}
