package com.mnu.mybatis.mappertest;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mnu.mybatis.domain.DeptDTO;
import com.mnu.mybatis.mapper.EmpMapper;

@SpringBootTest
public class EmpMapperTest {
	//로그 출력용 클래스 생성
	private static final Logger log = 
			LoggerFactory.getLogger(EmpMapperTest.class);
	
	//주입
	@Autowired
	private EmpMapper mapper;
	/*	
	//테스트
	@Test
	public void empCountTest() {
		log.info("사원 수 : " + mapper.empCount());
	}
	
	@Test
	public void empDnoCountTest() {
		int count = mapper.empDnoCount(10);
		log.info("부서번호 10인 사원 수 : " + count);
	}
	
	@Test
	public void empListTest() {
		mapper.empList().forEach(emp->log.info(emp.toString()));
	}
	
	@Test
	public void empListTest2() {
		List<EmpDTO> list = mapper.empList();
	}
	
	
	
	@Test
	public void empDnoListTest() {
		mapper.empDnoList(10).forEach(emp->log.info(emp.toString()));
	}
	
	
	@Test
	public void empEnoListTest() {
		log.info(mapper.empEnoList(7788).toString());
	}
	
	
	@Test
	public void deptWriteTest() {
		DeptDTO dto = new DeptDTO();
		dto.setDno(60);
		dto.setDname("자재부");
		dto.setLoc("목포");
		
		int row = mapper.deptWrite(dto);
		log.info("결과 : " + row);
	}
	
	
	@Test
	public void deptUpdateTest() {
		DeptDTO dto = new DeptDTO();
		dto.setDno(60);
		dto.setDname("인사부");
		dto.setLoc("대전");
		
		int row = mapper.deptUpdate(dto);
		log.info("결과 : " + row);
	}
	*/
	@Test
	public void deptDeleteTest() {
		log.info("삭제 결과 : " + mapper.deptDelete(60));
	}
}
