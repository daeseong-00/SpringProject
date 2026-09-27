package com.mnu.sample.mappertest;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mnu.sample.domain.PageSearchDTO;
import com.mnu.sample.mapper.PdsMapper;

@SpringBootTest
public class PdsMapperTest {
	//로그 출력
		private static final Logger log =
				LoggerFactory.getLogger(PdsMapperTest.class);
		
	@Autowired
	private PdsMapper pdsMapper;
	
	@Test
	public void pdsCountTest() {
		log.info("총 게시글 수 : " + pdsMapper.pdsCount());
	}
	
	@Test
	public void pdsCountSearchTest() {
		String search = "name";
		String key = "이";
		log.info("총 검색 게시글 수 : " + pdsMapper.pdsCountSearch(search, key));
	}
	/*
	
	@Test
	public void pdsListTest() {
		pdsMapper.pdsList().forEach(pds->log.info(pds.toString()));
	}
	
	
	@Test
	public void pdsListSearchTest() {
		String search = "name";
		String key = "이";
		pdsMapper.pdsListSearch(search, key).forEach(pds->log.info(pds.toString()));
	}

	
	@Test
	public void pdsListPageTest() {
		PageSearchDTO dto = new PageSearchDTO();
		dto.setOffset(0);
		dto.setMaxlist(10);
		pdsMapper.pdsListPage(dto).forEach(pds->log.info(pds.toString()));
	}
		
	@Test
	public void pdsListSearchPageTest() {
		PageSearchDTO dto = new PageSearchDTO();
		dto.setOffset(0);
		dto.setMaxlist(10);
		dto.setSearch("name");
		dto.setKey("이왕건");
		pdsMapper.pdsList(dto).forEach(pds->log.info(pds.toString()));
	}
	*/
}
