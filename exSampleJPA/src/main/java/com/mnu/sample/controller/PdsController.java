package com.mnu.sample.controller;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriUtils;

import com.mnu.sample.dto.PdsRequestDTO;
import com.mnu.sample.service.PdsService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("Pds")
public class PdsController {
	//로그 출력용
	private static final Logger log =
			LoggerFactory.getLogger(PdsController.class);
	
	@Value("${file.dir}") // application.yml 등에 설정된 경로
	private String fileDir;
	
	private final PdsService pdsService;//서비스 주입
	
	//전체(검색 X, 페이지 X)
	@GetMapping("pds_list")
	public String pdsList(@ModelAttribute("page") int page, Model model) {
		log.info("Pds Call : pds_list");
		model.addAttribute("pList", pdsService.pdsList());
		model.addAttribute("totcount", pdsService.pdsCount());
		
		return "/Pds/pds_list";
	}
	
	//등록 폼
	@GetMapping("pds_write")
	//public String pdsWrite(@ModelAttribute("page") int page) {
	public String pdsWrite(@RequestParam("page") int page, Model model) {
		log.info("Pds Call : pds_write : " + page);
		model.addAttribute("page", page);
		return "/Pds/pds_write";
		//return "/Pds/pds_write?page=" + page; // 불가함
	}
	
	@PostMapping("pds_write")
	public String pdsWritePro(@RequestParam("page") int page, MultipartHttpServletRequest request, Model model) {
		PdsRequestDTO pdsDTO = new PdsRequestDTO();
		pdsDTO.setName(request.getParameter("name"));
		pdsDTO.setSubject(request.getParameter("subject"));
		pdsDTO.setContents(request.getParameter("contents"));
		pdsDTO.setPass(request.getParameter("pass"));
		
		//파일 저장경로 설정
		//String path = request.getServletContext().getRealPath("/upload/");
		MultipartFile mf = request.getFile("filename");
		String fileName = mf.getOriginalFilename();
		
		File file = new File(fileDir+fileName);
		try {
			mf.transferTo(file);//파일저장(로컬 폴더 C:/Users/websnet/upload/xxxx.xxx 저장)
		}catch(Exception e) {
			e.printStackTrace();
		}
		pdsDTO.setFilename(fileName);//db에 파일 이름만 저장
		int row = pdsService.pdsWrite(pdsDTO);
		//return "redirect:pds_list?page=1";// 경고없이 리스트로 이동(controller)
		return "redirect:pds_list?page=" + page;// 경고없이 리스트로 이동(controller)
/*
		model.addAttribute("row", row);
		return "/Pds/pds_write_pro";
*/		
	}
	

	// 기존 업로드와 동일한 경로를 얻기 위해 request 주입
	// 파일다운로드
	@GetMapping("down_load")
	public ResponseEntity<Resource> downloadFile( 
			  @RequestParam("filename") String filename,
                                              HttpServletRequest request) {        
        try {
            // 1. 업로드 때와 동일한 서블릿 컨텍스트 상의 실제 물리 경로 획득
        		//@Value("${file.dir}") // application.yml 등에 설정된 경로
        		//private String fileDir;
           
            // 2. 보안을 위해 상위 디렉토리 접근 차단(.normalize()) 및 경로 병합
            Path path = Paths.get(fileDir).resolve(filename).normalize();
            Resource resource = new UrlResource(path.toUri());

            // 3. 파일 존재 및 읽기 가능 여부 체크
            if (!resource.exists() || !resource.isReadable()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "파일을 찾을 수 없습니다: " + filename);
            }

            // 4. 한글 파일명 깨짐 방지 인코딩
            String encodedFilename = UriUtils.encode(filename, StandardCharsets.UTF_8);
            
            // 5. 다운로드 창을 띄우기 위한 Content-Disposition 설정
            String contentDisposition = "attachment; filename=\"" + encodedFilename + "\"";

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                    .body(resource);

        } catch (MalformedURLException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "파일 경로 오류가 발생했습니다.");
        }
    }	

}