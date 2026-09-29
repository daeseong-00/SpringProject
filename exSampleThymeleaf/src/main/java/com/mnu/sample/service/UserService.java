package com.mnu.sample.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mnu.sample.domain.UserDTO;
import com.mnu.sample.mapper.UserMapper;
import com.mnu.sample.util.UserSHA256;

import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.service.DefaultMessageService;
@Service
public class UserService {
	//coolssms 값 저장
	@Value("${coolsms.apikey}")
	private String apiKey;
	
	@Value("${coolsms.apisecret}")
	private String apiSecret;
	
	@Value("${coolsms.fromnumber}")
	private String fromNumber;
	
	@Value("${coolsms.url}")
	private String url;
	
	//UserMapper 주입
	@Autowired
	private UserMapper userMapper;

	//1. id 중복 검사
	public int userIdCheck(String userid) {
		return userMapper.userIdCheck(userid);
	}
	
	//인증번호 생성용 메소드(4 ~ 6자리)
	private String tempRandomNumber() {
		Random r = new Random();
		StringBuffer numStr = new StringBuffer();
		for(int i=0; i<4; i++) { //6자리 생성시 i<6
			numStr.append(r.nextInt(10));
		}
		return numStr.toString();
	}
	//2. SMS 인증번호 발송
	public String sendSMS(String phoneNumber) {
		String tempNum = tempRandomNumber();//인증번호 생성메소드 호출
		DefaultMessageService messageService = 
				NurigoApp.INSTANCE.initialize(apiKey, apiSecret, url);
		Message message = new Message();
		message.setFrom(fromNumber);
		message.setTo(phoneNumber);
		message.setText("인증번호 : " + tempNum);
		
		messageService.sendOne(new SingleMessageSendingRequest(message));
		
		return tempNum;
	}

	//2. 회원가입
	public int userWrite(UserDTO userDTO) {
		//비번 암호화
		userDTO.setPasswd(UserSHA256.getSHA256(userDTO.getPasswd()));
		if(userDTO.getEmail2()!=null) {
			userDTO.setEmail(userDTO.getEmail1()+"@"+userDTO.getEmail2());
		}else {
			userDTO.setEmail(userDTO.getEmail1()+"@"+userDTO.getEmail3());
		}
		return userMapper.userWrite(userDTO);
	}

	//3. 회원 로그인
	public UserDTO userLogin(UserDTO userDTO) {
		//비번 암호화
		userDTO.setPasswd(UserSHA256.getSHA256(userDTO.getPasswd()));
		
		return userMapper.userLogin(userDTO);
	}
	
	//4. 로그인 날짜 업데이트
	public void userLastTimeUpdate(String userid) {
		userMapper.userLastTimeUpdate(userid);
	}
	
	//5. 이메일 인증 구현
	
	//5. userid를 이용한 사용자 검색
	public UserDTO getUserid(String userid) {
		
		
		return userMapper.getUserid(userid);
	}
	
}