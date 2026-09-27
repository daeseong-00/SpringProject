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
	//로그 출력
	private static final Logger log =
			LoggerFactory.getLogger(AdminPdsController.class);

	//게시판 리스트
	@GetMapping("pds_list")
	public String pdsList() {
		log.info("Admin Pds Call : pds_list");
		
		return "Admin/pds_list";
	}
	//게시판 등록 폼
	@GetMapping("pds_write")
	public String pdsWrite() {
		log.info("Admin Pds Call : pds_write");
			
		return "Admin/pds_write";
	}
	//게시판 등록 처리
	@PostMapping("pds_write")
	public String pdsWritePro() {
		log.info("Admin Pds Call : pds_write");
			
		return "Admin/pds_list";
	}
	//게시판 등록
	@GetMapping("pds_view")
	public String pdsView() {
		log.info("Admin Pds Call : pds_view");
			
		return "Admin/pds_view";
	}		
		
}
