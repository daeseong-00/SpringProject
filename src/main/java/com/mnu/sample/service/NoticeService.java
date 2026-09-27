package com.mnu.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mnu.sample.domain.NoticeDTO;
import com.mnu.sample.domain.NoticeDTO;
import com.mnu.sample.domain.PageSearchDTO;
import com.mnu.sample.mapper.NoticeMapper;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class NoticeService {
    @Autowired
    private NoticeMapper noticeMapper;
    
    // 1. 전체 글 수 카운트
    public int noticeCount() {
        return noticeMapper.noticeCount();
    }
        
    //2. 검색조건에 맞는 공지사항 카운트
  	public int noticeSearchCount(PageSearchDTO pageSearchDTO) {
  		return noticeMapper.noticeSearchCount(pageSearchDTO);
  	}
  		
  	//3. 공지사항 목록(검색+페이지) 겸용
  	public List<NoticeDTO> noticeList(PageSearchDTO pageSearchDTO){
  		return noticeMapper.noticeList(pageSearchDTO);
  	}
  	
  	//4. idx에 해당하는 글 목록(View, modify)
  	public NoticeDTO noticeView(int idx,  HttpServletRequest request, HttpServletResponse response) {
		//쿠키설정
		boolean bool = false;
		Cookie info = null;
		Cookie[] cookies = request.getCookies();
		for(int i=0; i<cookies.length; i++) {
			info = cookies[i];
			if(info.getName().equals("noticeCookie"+idx)) {
				bool = true;
				break;
			}
		}
		String str = ""+System.currentTimeMillis();
		if(!bool) {
			//쿠키생성
			info = new Cookie("noticeCookie"+idx, str);
			//info.setMaxAge(24*60*60);//1일
			info.setMaxAge(60*5);//5분
			response.addCookie(info);
			noticeMapper.noticeHits(idx);	
		}
		
		NoticeDTO notice = noticeMapper.noticeView(idx);
		notice.setContents(notice.getContents().replace("\n", "<br>"));
		
		return notice;
		
	}
  	
  	//5. 공지사항 등록(write) 처리
  	public int noticeWrite(NoticeDTO noticeDTO) {
  		return noticeMapper.noticeWrite(noticeDTO);
  	}
  	
  	//6. 공지사항 수정 처리
  	public int noticeModify(NoticeDTO noticeDTO) {
  		return noticeMapper.noticeModify(noticeDTO);
  	}
  	
  	//7. 공지사항 삭제
  	public int noticeDelete(NoticeDTO noticeDTO) {
  		return noticeMapper.noticeDelete(noticeDTO);
  	}
}