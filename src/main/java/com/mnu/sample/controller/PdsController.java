package com.mnu.sample.controller;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriUtils;

import com.mnu.sample.domain.PageSearchDTO;
import com.mnu.sample.domain.PdsDTO;
import com.mnu.sample.service.PdsService;
import com.mnu.sample.util.PageIndex;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("Pds")
public class PdsController {
	//로그 출력용
	private static final Logger log =
			LoggerFactory.getLogger(PdsController.class);
	
	@Autowired
	private PdsService pdsService;

	//Get, Post 겸용 (검색 O, 페이징 O)
	@RequestMapping(value="pds_list", method = {RequestMethod.GET, RequestMethod.POST})
	public String pdsList(@ModelAttribute("page") int page, PageSearchDTO pageSearchDTO, Model model) {

		log.info("Pds Call : pds_list");
		
		int nowpage = page ; //넘어온 페이지 저장
		int maxlist = 10; //페이지당 글수
		int totpage = 1; //총 페이지수
		
		int totcount = 0;//총 글수
		if(pageSearchDTO.getKey() != null)
			totcount = pdsService.pdsCountSearch(pageSearchDTO.getSearch(), pageSearchDTO.getKey());//총 글수
		else
			totcount = pdsService.pdsCount();
		
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
		
		List<PdsDTO> pList = pdsService.pdsList(pageSearchDTO);
		String pageSkip = null;
		
		if(pageSearchDTO.getKey() != null) {
			pageSkip = PageIndex.pageListHan(nowpage, totpage, "pds_list", maxlist, pageSearchDTO.getSearch(), pageSearchDTO.getKey());
		}else {
			pageSkip = PageIndex.pageList(nowpage, totpage, "pds_list", maxlist);				
		}
		
		model.addAttribute("totcount", totcount);
		model.addAttribute("totpage", totpage);
		model.addAttribute("listcount", listcount);
		model.addAttribute("pList", pList);
		model.addAttribute("pageSkip", pageSkip);
		
		return "Pds/pds_list";
	}


	// 등록 폼
	@GetMapping("pds_write")
	public String pdsWrite(@ModelAttribute("page") int page) {
		log.info("Pds Call : pds_write");
		
		return "Pds/pds_write";
	}
	
	// 등록 처리
	@PostMapping("pds_write")
	public String pdsWritePro(@ModelAttribute("page") int page, MultipartHttpServletRequest request) {
		log.info("Pds Call : pds_write_pro");
		PdsDTO pdsDTO = new PdsDTO();
		
		pdsDTO.setName(request.getParameter("name"));
		pdsDTO.setEmail(request.getParameter("email"));
		pdsDTO.setSubject(request.getParameter("subject"));
		pdsDTO.setContents(request.getParameter("contents"));
		pdsDTO.setPass(request.getParameter("pass"));
		
		MultipartFile mf = request.getFile("filename");
		//저장경로 설정 src/main/webapp/upload 폴더 생성
		String path = request.getServletContext().getRealPath("/upload/");
		//파일이름 추출
		String fileName = mf.getOriginalFilename();
		long fileSize = mf.getSize();//파일용량 
		
		pdsDTO.setFilename(fileName);
		
		//실제 파일 저장
		File file = new File(path+fileName);//파일 객체 생성
		try {
			mf.transferTo(file);//파일저장
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		pdsService.pdsWrite(pdsDTO);
		
		return "redirect:/Pds/pds_list?page="+page;//컨트롤러
	}
	
	// 기존 업로드와 동일한 경로를 얻기 위해 request 주입
	// 파일다운로드
	@GetMapping("down_load")
	public ResponseEntity<Resource> downloadFile( 
			  @RequestParam("filename") String filename,
                                              HttpServletRequest request) {        
        try {
            // 1. 업로드 때와 동일한 서블릿 컨텍스트 상의 실제 물리 경로 획득
            String uploadPath = request.getServletContext().getRealPath("/upload/");
            
            // 2. 보안을 위해 상위 디렉토리 접근 차단(.normalize()) 및 경로 병합
            Path path = Paths.get(uploadPath).resolve(filename).normalize();
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
	
	//상세보기(view)
	@GetMapping("pds_view")
	public String pdsView(@ModelAttribute("page") int page, 
										@RequestParam("idx") int idx, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		model.addAttribute("pds", pdsService.pdsView(idx, request, response));
		return "Pds/pds_view";
	}
	
	//수정
	@GetMapping("pds_modify")
	public String pdsModify(@ModelAttribute("page") int page, @RequestParam("idx") int idx , Model model) {
		
		model.addAttribute("pdsDTO", pdsService.pdsModify(idx));
		return "Pds/pds_modify";
	}

	//수정처리
	@PostMapping("pds_modify")
	public String pdsModifyPro(@ModelAttribute("page") int page, MultipartHttpServletRequest request) {
		log.info("Call  :   pdsModifyPro  ");
		PdsDTO dto = new PdsDTO();
		dto.setIdx(Integer.parseInt(request.getParameter("idx")));
		dto.setName(request.getParameter("name"));
		dto.setPass(request.getParameter("pass"));
		dto.setEmail(request.getParameter("email"));
		dto.setSubject(request.getParameter("subject"));
		dto.setContents(request.getParameter("contents"));
		String oldfilename = request.getParameter("oldfilename");
		
		MultipartFile mf = request.getFile("filename");
		// 업로드 경로 설정
		//String path=request.getRealPath("/upload/");
		String path = request.getServletContext().getRealPath("/upload/");
		String fileName=mf.getOriginalFilename();
		if(fileName.equals("")) {
			dto.setFilename(oldfilename);
		}else {
			File newFile=new File(path+fileName);
			File oldFile=new File(path+oldfilename);
			try {
				if(oldFile.exists()) {
					oldFile.delete();// 파일삭제
				}
				mf.transferTo(newFile);
			}catch(Exception e) {
				e.printStackTrace();
			}
			dto.setFilename(fileName);
		}
		
		pdsService.pdsModifyPro(dto);
		return "redirect:/Pds/pds_list?page="+page;// 매핑정보
	}

	//삭제폼
	@GetMapping("pds_delete")
	public String pdsDelete(@ModelAttribute("page") int page, @ModelAttribute("idx") int idx) {
		return "/Pds/pds_delete";
	}

	//삭제처리
	@PostMapping("pds_delete")
	public String pdsDeletePro(@ModelAttribute("page") int page, PdsDTO dto, Model model, HttpServletRequest request) {
		log.info("Call  :  pds_delete (삭제 처리)" );
		String filename = pdsService.pdsSearchFile(dto.getIdx());//파일검색
		int row=pdsService.pdsDelete(dto);
		
		model.addAttribute("row", row);
		//첨부파일삭제
		if(row==1) {
			if(filename != null) {
				File file = new File(request.getServletContext().getRealPath("/upload/") + filename);
				file.delete();
			}
		}	
		
		return "/Pds/pds_delete_pro";
	}
	
/*	
	//삭제처리
	// RedirectAttributes rttr : 다른 컨트롤러에게 데이터를 전달할때 사용하는 인터페이스
	// 리다이렉트 수행 시 일회성 데이터를 안전하게 전달, 새로고침이나 URL 노출 문제를 해결
	@PostMapping("pds_delete")
	public String pdsDeletePro(@ModelAttribute("page") int page, PdsDTO dto, RedirectAttributes rttr, HttpServletRequest request) {
		log.info("Call  :  pds_delete (삭제 처리)" );
		//PdsDTO pds = pdsService.pdsSearchFile(dto.getIdx());//파일검색
		int row=pdsService.pdsDelete(dto);
		
		rttr.addFlashAttribute("row", row);
		//첨부파일삭제
		if(row==1) {
			if(pds.getFilename() != null) {
				File file = new File(request.getServletContext().getRealPath("/upload/") + pds.getFilename());
				file.delete();
			}
		}	
		
		return "/Pds/pds_delete_pro";//Controller
	}

*/	
	//삭제처리 알림
	@GetMapping("pds_delete_pro")
	public void pdsDeletePass() {
		log.info("pdsDeletePro() . OK.......");
	}

}
