package com.mnu.sample.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mnu.sample.domain.BoardDTO;
import com.mnu.sample.domain.NoticeDTO;
import com.mnu.sample.domain.PageSearchDTO;
import com.mnu.sample.domain.PdsDTO;

@Mapper
public interface PdsMapper {
	// 최근 글 n개 검색
	public List<PdsDTO> pdsTopList(int num);
	
	//1. 전체 글수 카운트
	public int pdsCount();
	
	//2. 검색조건에 해당하는 글수
	public int pdsCountSearch(String search, String key);
	
	//3. 검색조건 + 페이지 인덱싱 리스트
	public List<PdsDTO> pdsList(PageSearchDTO pageSearchDTO);

	//5. 글 등록 
	public int pdsWrite(PdsDTO pdsDTO);
	
	//6. 특정글 검색(view, 수정)
	public void pdsHits(int idx);//조회수 증가
	
	public PdsDTO pdsView(int idx);
	
	//7. 수정처리 처리
	public int pdsModifyPro(PdsDTO pdsDTO);
	
	//8. 삭제처리
	public int pdsDelete(PdsDTO pdsDTO);
	
	//8. 삭제처리 첨부파일 검색
	public String pdsSearchFile(int idx);
}
