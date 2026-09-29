package com.mnu.sample.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mnu.sample.domain.Role;
import com.mnu.sample.domain.UserDTO;
import com.mnu.sample.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor //자동주입 
public class UserService {
	//mapper 주입
	private final UserMapper userMapper;
	//비밀번호 암호화 Bean
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	//1. id 중복검사
	public int userIdCheck(String userid) {
		return userMapper.userIdCheck(userid);
	}
		
	//2. 회원가입
	public int userWrite(UserDTO userDTO) {
		userDTO.setRole(Role.ROLE_USER);//기본 권한은 사용자
		//비밀번호 암호화
		userDTO.setPasswd(bCryptPasswordEncoder.encode(userDTO.getPasswd()));
		return userMapper.userWrite(userDTO);
	}
		
	//3. 아이디를 이용한 사용자 검색(security login)

		
	//4. 로그인 날짜 업데이트
	public void userLastTimeUpdate(String userid) {
		userMapper.userLastTimeUpdate(userid);
	}
}
