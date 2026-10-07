package com.mnu.sample.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.mnu.sample.dto.NoticeResponseDTO;
import com.mnu.sample.service.NoticeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("Notice")
public class NoticeController {
	//로그 출력용
	private static final Logger log =
			LoggerFactory.getLogger(NoticeController.class);
	
	private final NoticeService noticeService;
	
	//검색 + 페이지 처리 + get + post
	@GetMapping("notice_list")
	public String noticeListSearchPage(@RequestParam(value="search",required=false) String search, 
			@RequestParam(value="key", required=false) String key, @PageableDefault(size=10) Pageable pageable,
			Model model) {
		Page<NoticeResponseDTO> result = noticeService.noticeListSearchPage(search, key, pageable);
		
		model.addAttribute("nList", result);
		model.addAttribute("totcount", result.getTotalElements()); // ⭐ 이 부분이 꼭 있어야 합니다!
		model.addAttribute("search", search);
		model.addAttribute("key", key);
		
		return "Notice/notice_list";
	}
	
		
	//리스트에서 제목 선택 시 idx를 이용한 상세보기(view)
	@GetMapping("notice_view")
	public String noticeView(@RequestParam("idx") int idx, @RequestParam(value="page", defaultValue="1") int page, Model model) {
		log.info("Notice Call : notice_view");
		NoticeResponseDTO notice = noticeService.noticeView(idx);
		model.addAttribute("notice", notice);
		model.addAttribute("page", page); // ⭐ 현재 페이지 번호 모델에 추가
		model.addAttribute("newLineChar","\n");
		return "Notice/notice_view";
	}
	
	//삭제 폼
	@GetMapping("notice_delete")
	public String noticeDelete(@ModelAttribute("idx") int idx, @ModelAttribute("page") int page) {
		log.info("Notice Call : notice_delete");

		return "Notice/notice_delete";
	}
	
	//삭제 처리
	@PostMapping("notice_delete")
	public String noticeDeletePro(@RequestParam("idx") int idx, String pass, Model model) {
		log.info("Notice Call : notice_delete_pro");
		int row = noticeService.noticeDelete(idx);
		model.addAttribute("row", row);
		return "Notice/notice_delete_pro";
	}
	
}
