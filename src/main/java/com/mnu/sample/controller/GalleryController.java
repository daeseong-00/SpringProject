package com.mnu.sample.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("Gallery")
public class GalleryController {
	//로그 출력
		private static final Logger log =
				LoggerFactory.getLogger(GalleryController.class);
		
		//자료실 리스트
				@GetMapping("list")
				public String galleryList() {
					log.info("User Call : galleryList");
						
					return "Gallery/gallery_list";
				}
	    //자료실 읽기
					@GetMapping("view")
					public String galleryView() {
						log.info("User Call : galleryView");
							
						return "Gallery/gallery_view";
					}	
				
			}
