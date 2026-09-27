package com.mnu.exmvc.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mnu.exmvc.domain.DeptDTO;

@Controller
@RequestMapping("")
public class ExamController {
	//로그 출력용 객체 생성
	private static final Logger log = 
			LoggerFactory.getLogger(ExamController.class);
	
	@GetMapping("index")
	public void mainIndex() {
		log.info("Call : index");
	}
	
	@GetMapping("index1")
	public void mainIndex1() {
		log.info("Call : index1");
	}
	
	@GetMapping("index2")
	public String mainIndex2() {
		log.info("Call : index2");
		return "/Exam/test";//jsp파일이어야 된다
	}
	
	//파라미터 수집(파라미터로 사용된 변수와 전달되는 변수가 같을 경우)
	@GetMapping("ex01")
	public void ex01(String name, int idx) {
		log.info("name : " + name);
		log.info("idx : " + idx);
	}
	
	//파라미터 수집(파라미터로 사용된 변수와 전달되는 변수가 다를 경우)
		@GetMapping("ex02")
		public void ex02(@RequestParam("name") String na, @RequestParam("idx") int no) {
			log.info("name : " + na);
			log.info("idx : " + no);
		}
		
	//파라미터 자동 수집(DTO VO)
		@GetMapping("ex03")
		public void ex03(DeptDTO dto) {
			log.info("dto : " + dto.toString());
			log.info("dno : " + dto.getDno());
			log.info("dname : " + dto.getDname());
			log.info("loc : " + dto.getLoc());
		}
		
		@GetMapping("ex04")
		public void ex04(@RequestParam("data") ArrayList<String> list) {
			log.info("list : " + list);
		}
		
	//전달(view)--> 스프링에서 전달자 (Model)
		@GetMapping("trans01")
		public String trans01(Model model) {
			model.addAttribute("name","홍길동");
			return "exam01";
		}
		
		@GetMapping("trans02")
		public String trans02(Model model) {
			List<String> list = new ArrayList();
			list.add("김학생");list.add("이학생");
			list.add("홍학생");list.add("박학생");
			list.add("윤학생");list.add("강학생");
			
			model.addAttribute("list", list);
			return "exam02";
		}
	//@ModelAttribute 전달자 / DTO 전달자 -> 전달되면 view에서는 첫글자를 소문자로 사용
		@GetMapping("trans03")
		public String trans03(DeptDTO dto, int page) {
			
			return "exam03";
		}
		
		@GetMapping("trans04")
		public String trans04(DeptDTO dto, @ModelAttribute("page") int page) {
			
			return "exam04";	
		}
		
	//response.sendRedirect ==> RedirectAttributes 1회성
	//RedirectAttributes -> 1. addAttribute() : 화면에 보임(url)
	//						2. addFlashAttribute() : 안보임(세션)
		
		@GetMapping("trans05")
		public String exam05(RedirectAttributes rttr) {
			//rttr.addAttribute("page", 10);
			rttr.addFlashAttribute("page",10);
			return "redirect:trans04";
		}
		
		@GetMapping("trans06")
		public @ResponseBody DeptDTO trans06() {
			DeptDTO dto = new DeptDTO();
			dto.setDno(10);
			dto.setDname("영업부");
			dto.setLoc("목포");
			
			return dto;
		}
		
		
		
	
}
