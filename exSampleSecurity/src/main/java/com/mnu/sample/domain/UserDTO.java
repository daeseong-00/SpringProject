package com.mnu.sample.domain;

import lombok.Data;

@Data
public class UserDTO {
	private String userid;
	private String name;
	private String passwd;
	private String gubun;
	private String tel;
	private String email;
	private String first_time;
	private String last_time;
	private Role role;
	
	
}
