package com.mnu.sample.dto;

import com.mnu.sample.entity.UserEntity;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class UserRequestDTO {
	@NotEmpty(message="아이디는 필수 입력사항입니다") //-> null, "", " " 모두 허용X
	//@NotNull(message="아이디는 필수 입력사항입니다") //-> null 값만 허용하지 않음("", " " 은 허용)
	private String userid;
	@NotEmpty(message="이름은 필수 입력사항입니다")
	private String name;
	@NotEmpty(message="비밀번호는 필수 입력사항입니다")
	private String passwd;
	private String tel;
	private String gubun;
	@Email(message="이메일 형식으로 입력하세요")
	private String email;
	
	@AssertTrue(message="이메일과 전화번호 중 하나는 필수 입력값입니다.")
	public boolean isEmailOrTelPresent() {
		if((email==null || email.trim().isEmpty()) && 
				(tel == null || tel.trim().isEmpty())) {
			return false;
		}
		return true;
	}
	
	//DTO에서 필요한 부분을 entity 화
		public UserEntity toEntity() {
			return UserEntity.builder()
					.userid(userid)
					.name(name)
					.passwd(passwd)
					.tel(tel)
					.email(email)
					.build();
		}
	
	
}
