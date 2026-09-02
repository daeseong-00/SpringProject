package com.mnu.sample.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("Pds")
public class PdsController {
	//로그 출력
		private static final Logger log =
				LoggerFactory.getLogger(PdsController.class);
		
		//자료실 리스트
				@GetMapping("list")
				public String pdsList() {
					log.info("User Call : pdsList");
						
					return "Pds/pds_list";
				}
	    //자료실 읽기
					@GetMapping("view")
					public String pdsView() {
						log.info("User Call : pdsView");
							
						return "Pds/pds_view";
					}	
				
			}
