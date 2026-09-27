package com.mnu.mybatis.dbtest;

import java.sql.Connection;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DataSourceTest {
	private static final Logger log = 
			LoggerFactory.getLogger(DataSourceTest.class);
		
	@Autowired// 자동 주입
	private SqlSessionFactory sqlSessionFactory;
	
	@Test
	public void bdTest() throws Exception{
		SqlSession session = sqlSessionFactory.openSession();
		Connection conn = session.getConnection();
		log.info("SqlSession : " + session);
		log.info("Connection : " + conn);
	}
	
}
