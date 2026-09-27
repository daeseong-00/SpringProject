package com.mnu.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TestMapper {
	@Select("select sysdate from dual")
	public String getTime();
	
	public String getTime2();
	//계정 수
	public int userCount();
	
}
