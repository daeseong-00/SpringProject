package com.mnu.sample.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mnu.sample.domain.NoticeDTO;
import com.mnu.sample.domain.PageSearchDTO;
import com.mnu.sample.service.NoticeService;
import com.mnu.sample.util.PageIndex;

@Controller
@RequestMapping("Notice")
public class NoticeController {
	//로그 출력용
	private static final Logger log =
			LoggerFactory.getLogger(NoticeController.class);
	//주입
	@Autowired
	private NoticeService noticeService;
	
	//공지사항 리스트(Post, Get 겸용)
	@RequestMapping(value="notice_list", method = {RequestMethod.GET, RequestMethod.POST})
	public String noticeList(@RequestParam(value = "page", defaultValue = "1") int page, PageSearchDTO pageSearchDTO, Model model) {
		log.info("Notice Call : notcie_list");
		
		int nowpage = page ; //넘어온 페이지 저장
		int maxlist = 10; //페이지당 글수
		int totpage = 1; //총 페이지수
		
		int totcount = 0;//총 글수
		if(pageSearchDTO.getKey() != null)
			totcount = noticeService.noticeSearchCount(pageSearchDTO);//총 글수
		else
			totcount = noticeService.noticeCount();
		
		// 총 페이지수 계산
		if(totcount % maxlist ==0)
			totpage = totcount / maxlist;
		else
			totpage = totcount / maxlist + 1;
				
		int offset = (nowpage - 1) * maxlist;
		
		//게시글 일련번호 출력용
		int listcount = totcount - ((nowpage-1) * maxlist);
		
		pageSearchDTO.setOffset(offset);
		pageSearchDTO.setMaxlist(maxlist);
		
		List<NoticeDTO> nList = noticeService.noticeList(pageSearchDTO);
		String pageSkip = null;
		if(pageSearchDTO.getKey() != null) {
			pageSkip = PageIndex.pageListHan(nowpage, totpage, "notice_list", maxlist, pageSearchDTO.getSearch(), pageSearchDTO.getKey());
		}else {
			pageSkip = PageIndex.pageList(nowpage, totpage, "notice_list", maxlist);				
		}
		
		model.addAttribute("totcount", totcount);
		model.addAttribute("totpage", totpage);
		model.addAttribute("listcount", listcount);
		model.addAttribute("nList", nList);
		model.addAttribute("pageSkip", pageSkip);

		return "Notice/notice_list";
	}

	//공지사항 뷰(상세보기)
	@GetMapping("notice_view")
	public String noticeView(@ModelAttribute("page") int page, int idx, Model model) {
		log.info("Notice Call : notcie_view");
		//조회수 증가(쿠키생성)
		noticeService.noticeHits(idx);
		
		NoticeDTO notice = noticeService.noticeSelect(idx);
		notice.setContents(notice.getContents().replace("\n", "<br>"));		
		model.addAttribute("notice", notice);
		return "Notice/notice_view";
	}

}