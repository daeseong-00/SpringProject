package com.mnu.sample.mappertest;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mnu.sample.domain.PageSearchDTO;
import com.mnu.sample.mapper.BoardMapper;

@SpringBootTest
public class BoardMapperTest {
	//로그 출력
		private static final Logger log =
				LoggerFactory.getLogger(BoardMapperTest.class);
		
	@Autowired
	private BoardMapper boardMapper;
/*	
	@Test
	public void boardCountTest() {
		log.info("총 게시글 수 : " + boardMapper.boardCount());
	}
	
	@Test
	public void boardCountSearchTest() {
		String search = "name";
		String key = "이";
		log.info("총 검색 게시글 수 : " + boardMapper.boardCountSearch(search, key));
	}

	
	@Test
	public void boardListTest() {
		boardMapper.boardList().forEach(board->log.info(board.toString()));
	}
	
	
	@Test
	public void boardListSearchTest() {
		String search = "name";
		String key = "이";
		boardMapper.boardListSearch(search, key).forEach(board->log.info(board.toString()));
	}

	
	@Test
	public void boardListPageTest() {
		PageSearchDTO dto = new PageSearchDTO();
		dto.setOffset(0);
		dto.setMaxlist(10);
		boardMapper.boardListPage(dto).forEach(board->log.info(board.toString()));
	}
		*/
	@Test
	public void boardListSearchPageTest() {
		PageSearchDTO dto = new PageSearchDTO();
		dto.setOffset(0);
		dto.setMaxlist(10);
		dto.setSearch("name");
		dto.setKey("이왕건");
		boardMapper.boardListSearchPage(dto).forEach(board->log.info(board.toString()));
	}
	
}
