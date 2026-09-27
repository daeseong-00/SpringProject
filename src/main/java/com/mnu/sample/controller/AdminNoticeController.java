package com.mnu.sample.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mnu.sample.domain.BoardDTO;
import com.mnu.sample.domain.NoticeDTO;
import com.mnu.sample.domain.PageSearchDTO;
import com.mnu.sample.service.NoticeService;
import com.mnu.sample.util.PageIndex;

@Controller
@RequestMapping("Admin/Notice")
public class AdminNoticeController {
	//로그 출력
	private static final Logger log =
			LoggerFactory.getLogger(AdminNoticeController.class);
	
	@Autowired
    private NoticeService noticeService;

	//공지사항 리스트
	//Get, Post 겸용 (검색 O, 페이징 O)
		@RequestMapping(value="notice_list", method = {RequestMethod.GET, RequestMethod.POST})
		public String noticeList(@RequestParam(value="page", defaultValue="1") int page, PageSearchDTO pageSearchDTO, Model model) {
			   log.info("Notice Call : notice_list");
		        
		        int nowpage = page;
		        int maxlist = 10;
		        int totpage = 1;
		        int totcount = 0;
		        
		        if(pageSearchDTO.getKey() != null)
		            totcount = noticeService.noticeSearchCount(pageSearchDTO);
		        else
		            totcount = noticeService.noticeCount();
		        
		        if(totcount % maxlist == 0)
		            totpage = totcount / maxlist;
		        else
		            totpage = totcount / maxlist + 1;
		                
		        int offset = (nowpage - 1) * maxlist;
		        int listcount = totcount - ((nowpage - 1) * maxlist);
		        
		        pageSearchDTO.setOffset(offset);
		        pageSearchDTO.setMaxlist(maxlist);
		        
		        List<NoticeDTO> nList = null;
		        String pageSkip = null;
		        if(pageSearchDTO.getKey() != null) {
		            nList = noticeService.noticeList(pageSearchDTO);
		            pageSkip = PageIndex.pageListHan(nowpage, totpage, "list", maxlist, pageSearchDTO.getSearch(), pageSearchDTO.getKey());
		        } else {
		            nList = noticeService.noticeList(pageSearchDTO);
		            pageSkip = PageIndex.pageList(nowpage, totpage, "list", maxlist);              
		        }
		        
		        model.addAttribute("page", nowpage);
		        model.addAttribute("totcount", totcount);
		        model.addAttribute("totpage", totpage);
		        model.addAttribute("listcount", listcount);
		        model.addAttribute("nList", nList);
		        model.addAttribute("pageSkip", pageSkip);
		        
		        return "Admin/notice_list";
		    }
	//공지사항 등록 폼
	@GetMapping("notice_write")
	public String noticeWrite(@RequestParam(value = "page", defaultValue = "1") int page, Model model) {
		log.info("Admin Notice Call : notice_write");
		model.addAttribute("page", page);	
		return "Admin/notice_write";
	}
	//공지사항 등록 처리
	@PostMapping("notice_write")
	public String noticeWritePro(@RequestParam(value = "page", defaultValue = "1") int page, NoticeDTO noticeDTO) {
		log.info("Admin Notice Call : notice_write");
		int row = noticeService.noticeWrite(noticeDTO);	
		return "redirect:/Admin/Notice/notice_list?page=" + page;
	}
	//공지사항 보기
	@GetMapping("notice_view")
	public String noticeView() {
		log.info("Admin Notice Call : notice_view");
			
		return "Admin/notice_view";
	}		
		
}
