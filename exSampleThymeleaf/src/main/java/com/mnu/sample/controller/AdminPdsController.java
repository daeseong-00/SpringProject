package com.mnu.sample.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("Admin/Pds")
public class AdminPdsController {
	//로그 출력용
	private static final Logger log =
			LoggerFactory.getLogger(AdminPdsController.class);

	//자료실 리스트
	@GetMapping("pds_list")
	public String pdsList() {
		log.info("Admin Pds Call : pds_list");
		
		return "Admin/pds_list";
	}
	
	

}
