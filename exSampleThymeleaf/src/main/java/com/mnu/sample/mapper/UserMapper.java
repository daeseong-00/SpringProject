package com.mnu.sample.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.mnu.sample.domain.UserDTO;

@Mapper
public interface UserMapper {
	//1. id 중복 검사
	public int userIdCheck(String userid);
	
	//2. 회원가입
	public int userWrite(UserDTO userDTO);
	
	//3. 로그인
	public UserDTO userLogin(UserDTO userDTO);
	
	//4. 로그인한 날짜 업데이트
	public void userLastTimeUpdate(String userid);
	
	//5. userid를 이용한 사용자 검색
	public UserDTO getUserid(String userid);
}
