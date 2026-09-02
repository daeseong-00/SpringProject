package com.mnu.sample.servicetest;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mnu.sample.mapper.BoardMapper;
import com.mnu.sample.mappertest.BoardMapperTest;

@SpringBootTest
public class BoardServiceTest {
	//로그 출력
			private static final Logger log =
					LoggerFactory.getLogger(BoardServiceTest.class);
			
	@Autowired
	private BoardMapper boardService;
	
	@Test
	public void boardCountTest() {
		log.info("총 게시글 수 : " + boardService.boardCount());
	}
	
}
