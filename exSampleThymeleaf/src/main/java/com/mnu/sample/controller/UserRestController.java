package com.mnu.sample.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mnu.sample.domain.UserDTO;
import com.mnu.sample.service.EmailService;
import com.mnu.sample.service.UserService;

@RestController
public class UserRestController {
	//로그 출력용
		private static final Logger log =
				LoggerFactory.getLogger(UserController.class);
		
	//UserService 주입
	@Autowired
	private UserService userService;
		
	//EmailService 주입
	@Autowired
	private EmailService emailService;
	
	//ID 중복검사
		
	@PostMapping("user_idCheck")
	public String userIdCheck(@RequestParam("userid") String userid) {
		log.info("User Call : user_idCheck");
		int row = userService.userIdCheck(userid);
		return String.valueOf(row);
	}
		
	//본인인증(SMS)
	@PostMapping("user_sms")
	public String smsSend(@RequestParam("tel") String tel) {
		String tempNum = userService.sendSMS(tel);
			
		log.info("인증번호 : " + tempNum);
		return tempNum;
	}

		
	//본인인증(email)
	@ResponseBody
	@PostMapping("user_email")
	public String emailSend(@RequestParam("email") String email) {
		String tempNum = emailService.sendEmail(email);
			
		log.info("인증번호 : " + tempNum);
		return tempNum;
	}
	
	@GetMapping("user_search")
	public UserDTO getUserid(@RequestParam("userid") String userid) {
		log.info("RestController Test : " + userid);
		
		return userService.getUserid(userid);
	}
}
