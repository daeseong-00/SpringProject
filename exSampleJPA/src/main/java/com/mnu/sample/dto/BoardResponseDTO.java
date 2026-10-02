package com.mnu.sample.dto;

import java.time.LocalDateTime;

import com.mnu.sample.entity.BoardEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@Getter
public class BoardResponseDTO {
	private int idx;
	private String name;
	private String pass;
	private String subject;
	private String contents;
	private int readcnt;
	private LocalDateTime regdate;
	private LocalDateTime updatedate;
	
	
	//entity  -> dto
	public BoardResponseDTO(BoardEntity entity) {
		this.idx = entity.getIdx();
        this.name = entity.getName();
        this.pass = entity.getPass();      
        this.subject = entity.getSubject(); 
        this.contents = entity.getContents();    
        this.readcnt = entity.getReadcnt();
        this.regdate = entity.getRegdate();
        this.updatedate = entity.getUpdatedate();
	}
}
