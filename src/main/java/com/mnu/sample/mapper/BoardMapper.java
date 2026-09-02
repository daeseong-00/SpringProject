package com.mnu.sample.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mnu.sample.domain.BoardDTO;

@Mapper
public interface BoardMapper {
	//1. 전체 글 수 카운트
	public int boardCount();
	
	//2. 검색조건에 해당하는 글 수
	public int boardCountSearch(String search, String key);
	
	//3. 전체목록 리스트
	public List<BoardDTO> boardList();
	
	//4. 검색조건에 해당하는 글 리스트
	public List<BoardDTO> boardListSearch(String search, String key);
	
	//5. 글 등록
	
	//6. 특정 글 검색(view, 수정)
	
	//7. 수정처리
	
	//8. 삭제처리
}
