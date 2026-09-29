package com.mnu.sample.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mnu.sample.domain.NoticeDTO;
import com.mnu.sample.domain.PageSearchDTO;

@Mapper
public interface NoticeMapper {
	//최근 글 n개 검색
		public List<NoticeDTO> noticeTopList(int num);
		
	//1. 전체 공지사항 카운트
	public int noticeCount();
	
	//2. 검색조건에 맞는 공지사항 카운트
	public int noticeSearchCount(PageSearchDTO pageSearchDTO);
	
	//3. 공지사항 목록(검색+페이지) 겸용
	public List<NoticeDTO> noticeList(PageSearchDTO pageSearchDTO);
	
	//4-1. 특정글 검색(view, 수정)
	public void noticeHits(int idx);//조회수 증가
	
	//4-2. idx에 해당하는 글 목록(View, modify) 사용
	public NoticeDTO noticeSelect(int idx);
	
	//5. 공지사항 등록(write) 처리
	public int noticeWrite(NoticeDTO noticeDTO);
	
	//6. 공지사항 수정(modify) 처리
	public int noticeModify(NoticeDTO noticeDTO);
	
	//7. 공지사항 삭제
	public int noticeDelete(int idx);
	
}


