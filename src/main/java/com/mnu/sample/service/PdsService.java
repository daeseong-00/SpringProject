package com.mnu.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mnu.sample.domain.BoardDTO;
import com.mnu.sample.domain.PageSearchDTO;
import com.mnu.sample.domain.PdsDTO;
import com.mnu.sample.mapper.PdsMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class PdsService {
	@Autowired
	private PdsMapper pdsMapper;
	//1. 전체 글수 카운트
	public int pdsCount() {
		return pdsMapper.pdsCount();
	}
	
	//2. 검색조건에 해당하는 글수
	public int pdsCountSearch(String search, String key) {
		return pdsMapper.pdsCountSearch(search, key);
	}
	
	//3. 검색조건 + 페이지 인덱싱 리스트
	public List<PdsDTO> pdsList(PageSearchDTO pageSearchDTO){
		return pdsMapper.pdsList(pageSearchDTO);
	}

	//5. 글 등록 
	public int pdsWrite(PdsDTO pdsDTO) {
		return pdsMapper.pdsWrite(pdsDTO);
	}
	
	//6. 특정글 검색(view, 수정)
	public void pdsHits(int idx){
		//조회수 증가
		pdsMapper.pdsHits(idx);
	}
	
	public PdsDTO pdsView(int idx, HttpServletRequest request, HttpServletResponse response) {
		
		return pdsMapper.pdsView(idx);
	}
	
	//7. 수정처리(폼)
	public PdsDTO pdsModify(int idx) {
		return pdsMapper.pdsView(idx);
	}
	
	//7. 수정처리 처리
	public int pdsModifyPro(PdsDTO pdsDTO) {
		return pdsMapper.pdsModifyPro(pdsDTO);
	}
	
	//8. 삭제처리 시 파일이 있는지 검색
	public String pdsSearchFile(int idx) {
		return pdsMapper.pdsSearchFile(idx);
	}

	//8. 삭제처리
	public int pdsDelete(PdsDTO pdsDTO) {
		return pdsMapper.pdsDelete(pdsDTO);
	}
}
