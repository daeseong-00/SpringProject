package com.mnu.sample.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mnu.sample.dto.UserRequestDTO;
import com.mnu.sample.dto.UserResponseDTO;
import com.mnu.sample.entity.UserEntity;
import com.mnu.sample.repository.UserRepository;
import com.mnu.sample.util.UserSHA256;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
	private final UserRepository userRepository;
	
	//id 중복 검사 - 1 int ver
	public int userIdCheck1(String userid) {
		if(userRepository.existsByUserid(userid))
			return 1;
		else
			return 0;
	}
	//id 중복 검사 - 2 boolean ver
	public boolean userIdCheck2(String userid) {
		return userRepository.existsByUserid(userid);
	}
	
	//등록 처리
	public int userInsert(UserRequestDTO userRequestDTO) {
		//비번 암호화
		userRequestDTO.setPasswd(UserSHA256.getSHA256(userRequestDTO.getPasswd()));
		try {
			userRepository.save(userRequestDTO.toEntity());
			return 1;
		}catch(Exception e) {
			return 0;
		}
		
	}
	
	//로그인 처리
	public UserResponseDTO userLogin(UserRequestDTO userRequestDTO) {
	    UserEntity userEntity = userRepository.findByUserid(userRequestDTO.getUserid())
	            .orElseThrow(() -> new IllegalArgumentException("ID 없음"));
	    
	    // getSHA256() 결과와 저장된 비밀번호를 비교하도록 괄호 위치 수정
	    if (!UserSHA256.getSHA256(userRequestDTO.getPasswd()).equals(userEntity.getPasswd())) {
	        throw new IllegalArgumentException("비번오류");
	    }
	    
	    return new UserResponseDTO(userEntity);
	}
	
	
}
