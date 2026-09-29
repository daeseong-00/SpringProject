package com.mnu.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mnu.sample.domain.NoticeDTO;
import com.mnu.sample.domain.PageSearchDTO;
import com.mnu.sample.mapper.NoticeMapper;

@Service
public class NoticeService {
	//Mapper 주입
	@Autowired
	private NoticeMapper noticeMapper;
	
	// 최근 글 n개 검색
	public List<NoticeDTO> noticeTopList(int num){
		return noticeMapper.noticeTopList(num);
		}
		
	//1. 전체 공지사항 카운트
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
	
	//4-1. idx에 해당하는 글 목록(View, modify) 사용
	public void noticeHits(int idx) {
		noticeMapper.noticeHits(idx);
	}
	
	//4-2. idx에 해당하는 글 목록(View, modify) 사용
	public NoticeDTO noticeSelect(int idx) {
		return noticeMapper.noticeSelect(idx);
	}

	//5. 공지사항 등록(write) 처리
	public int noticeWrite(NoticeDTO noticeDTO) {
		return noticeMapper.noticeWrite(noticeDTO);
	}
	
	//6. 공지사항 수정(modify) 처리
	public int noticeModify(NoticeDTO noticeDTO) {
		return noticeMapper.noticeModify(noticeDTO);
	}
	
	//7. 공지사항 삭제
	public int noticeDelete(int idx) {
		return noticeMapper.noticeDelete(idx);
	}

}
	
	
	
