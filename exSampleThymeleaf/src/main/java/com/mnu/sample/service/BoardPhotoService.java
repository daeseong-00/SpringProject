package com.mnu.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mnu.sample.domain.BoardPhotoDTO;
import com.mnu.sample.domain.PageSearchDTO;
import com.mnu.sample.mapper.BoardPhotoMapper;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class BoardPhotoService {
	@Autowired
	private BoardPhotoMapper boardPhotoMapper;
	

		
	//1. 전체 글수 카운트
	public int boardCount() {
		//int row = boardPhotoMapper.boardCount();
		//row++;
		//return row;
		return boardPhotoMapper.boardCount();
	}
	
	//2. 검색조건에 해당하는 글수
	public int boardCountSearch(String search, String key) {
		return boardPhotoMapper.boardCountSearch(search, key);
	}
	
	//3. 전체목록 리스트
	public List<BoardPhotoDTO> boardList(){
		return boardPhotoMapper.boardList();
	}
	
	//3-1. 전체목록 리스트(페이지 인덱싱)
	public List<BoardPhotoDTO> boardListPage(PageSearchDTO pageSearchDTO){
		return boardPhotoMapper.boardListPage(pageSearchDTO);
	}
	
	//4. 검색조건에 맞는 글 리스트
	public List<BoardPhotoDTO> boardListSearch(String search, String key){
		return boardPhotoMapper.boardListSearch(search, key);
	}

	//4-1. 검색조건 + 페이지 인덱싱 리스트
	public List<BoardPhotoDTO> boardListSearchPage(PageSearchDTO pageSearchDTO){
		return boardPhotoMapper.boardListSearchPage(pageSearchDTO);
	}

	//5. 글 등록 
	public int boardWrite(BoardPhotoDTO boardPhotoDTO) {
		return boardPhotoMapper.boardWrite(boardPhotoDTO);
	}
	//6. 특정글 검색(view, 수정), 조회수 증가
	public BoardPhotoDTO boardView(int idx,  HttpServletRequest request, HttpServletResponse response) {
		//쿠키설정
		boolean bool = false;
		Cookie info = null;
		Cookie[] cookies = request.getCookies();
		for(int i=0; i<cookies.length; i++) {
			info = cookies[i];
			if(info.getName().equals("boardCookie"+idx)) {
				bool = true;
				break;
			}
		}
		String str = ""+System.currentTimeMillis();
		if(!bool) {
			//쿠키생성
			info = new Cookie("boardCookie"+idx, str);
			//info.setMaxAge(24*60*60);//1일
			info.setMaxAge(60*5);//5분
			response.addCookie(info);
			boardPhotoMapper.boardHits(idx);	
		}
		
		BoardPhotoDTO board = boardPhotoMapper.boardView(idx);
		board.setContents(board.getContents().replace("\n", "<br>"));
		
		return board;
		
	}
	//7. 수정처리(폼)
	public BoardPhotoDTO boardModify(int idx) {
		return boardPhotoMapper.boardView(idx);
	}

	//7. 수정처리(처리)
	public int boardModifyPro(BoardPhotoDTO boardPhotoDTO) {
		
		return boardPhotoMapper.boardModifyPro(boardPhotoDTO);
	}

	//8. 삭제처리
	public int boardDelete(BoardPhotoDTO boardPhotoDTO) {
		
		return boardPhotoMapper.boardDelete(boardPhotoDTO);
	}

}
