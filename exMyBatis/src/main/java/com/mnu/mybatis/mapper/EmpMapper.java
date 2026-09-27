package com.mnu.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mnu.mybatis.domain.DeptDTO;
import com.mnu.mybatis.domain.EmpDTO;

@Mapper
public interface EmpMapper {
	//메소드 정의
	//1. emp테이블이 존재하는 총 사원 수(튜플) 카운트
	public int empCount();
	
	//2. 부서번호가 xx인 사원 수
	public int empDnoCount(int dno);
	
	//3. 전체 목록
	public List<EmpDTO> empList();
	
	//4. 특정 부서의 사원 출력
	public List<EmpDTO> empDnoList(int dno);
	
	//5. 특정 사원의 정보 출력
	public EmpDTO empEnoList(int eno);
	
	//6. 등록
	public int deptWrite(DeptDTO dto);
	
	//7. 수정
	public int deptUpdate(DeptDTO dto);
	
	//8. 삭제
	public int deptDelete(int dno);
	
}
