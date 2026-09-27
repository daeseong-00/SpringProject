package com.mnu.maven.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MavenController {
	@GetMapping("/Test")
	public String test() {
		return "test";
	}
	
	@GetMapping("/Exam")
	public String exam() {
		return "exam";
	}
}
