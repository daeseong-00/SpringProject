package com.mnu.sample.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mnu.sample.domain.NoticeDTO;
import com.mnu.sample.domain.PageSearchDTO;
import com.mnu.sample.service.NoticeService;
import com.mnu.sample.util.PageIndex;

@Controller
@RequestMapping("Admin/Notice")
public class AdminNoticeController {
	//로그 출력용
	private static final Logger log =
			LoggerFactory.getLogger(AdminNoticeController.class);
	
	//서비스 주입
	@Autowired
	private NoticeService noticeService;
	
	//공지사항 리스트(Post, Get 겸용)
	@RequestMapping(value="notice_list", method = {RequestMethod.GET, RequestMethod.POST})
	public String noticeList(@ModelAttribute("page") int page, PageSearchDTO pageSearchDTO, Model model) {
		log.info("Admin Notice Call : notcie_list");
		
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

		return "Admin/notice_list";
	}
	
	//공지사항 등록 폼
	@GetMapping("notice_write")
	public String noticeWrite(@ModelAttribute("page") int page) {
		log.info("Admin Notice Call : notcie_write");
		
		return "Admin/notice_write";
	}
	
	//공지사항 등록 폼
	@PostMapping("notice_write")
	public String noticeWritePro(@ModelAttribute("page") int page, NoticeDTO noticeDTO) {
		log.info("Admin Notice Call : notcie_write");
		int row = noticeService.noticeWrite(noticeDTO);
		
		return "redirect:notice_list?page="+page;//컨트롤러
	}

	//공지사항 뷰(상세보기)
	@GetMapping("notice_view")
	public String noticeView(@ModelAttribute("page") int page, int idx, Model model) {
		log.info("Admin Notice Call : notcie_view");
		NoticeDTO notice = noticeService.noticeSelect(idx);
		notice.setContents(notice.getContents().replace("\n", "<br>"));		
		model.addAttribute("notice", notice);
		return "Admin/notice_view";
	}
	
	//공지사항 수정 폼
	@GetMapping("notice_modify")
	public String noticeModify(@ModelAttribute("page") int page, int idx, Model model) {
		log.info("Admin Notice Call : notcie_modify");
		NoticeDTO notice = noticeService.noticeSelect(idx);
		model.addAttribute("notice", notice);
		return "Admin/notice_modify";
	}
		
	//공지사항 수정처리
	@PostMapping("notice_modify")
	public String noticeModifyPro(@ModelAttribute("page") int page, NoticeDTO noticeDTO) {
		log.info("Admin Notice Call : notcie_modify_pro");
		int row = noticeService.noticeModify(noticeDTO);
		
		return "redirect:notice_list?page="+page;//컨트롤러
	}
	
	//공지사항 삭제
	@GetMapping("notice_delete")
	public String noticeDelete(@ModelAttribute("page") int page, int idx) {
		log.info("Admin Notice Call : notcie_delete");
		int row = noticeService.noticeDelete(idx);
		
		return "redirect:notice_list?page="+page;//컨트롤러
	}
	

}
