package com.mnu.sample.domain;

import lombok.Data;

@Data
public class PdsDTO {
	private int idx;
	private String name;
	private String email;
	private String subject;
	private String contents;
	private String pass;
	private String filename;
	private String regdate;
	private int readcnt;
}
