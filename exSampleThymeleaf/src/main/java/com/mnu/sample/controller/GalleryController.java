package com.mnu.sample.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("Gallery")
public class GalleryController {
	//로그 출력용
	private static final Logger log =
			LoggerFactory.getLogger(GalleryController.class);

	//공지사항 리스트
	@GetMapping("gallery_list")
	public String galleryList() {
		log.info("Gallery Call : gallery_list");
		
		return "Gallery/gallery_list";
	}

}
