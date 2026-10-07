package com.mnu.sample.dto;

import java.time.LocalDateTime;

import com.mnu.sample.entity.NoticeEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@Getter
public class NoticeResponseDTO {
	private int idx;
	private String adid;
	private String subject;
	private String contents;
	private int readcnt;
	private LocalDateTime regdate;

	
	
	//entity  -> dto
	public NoticeResponseDTO(NoticeEntity entity) {
		this.idx = entity.getIdx();
        this.adid = entity.getAdid();      
        this.subject = entity.getSubject(); 
        this.contents = entity.getContents();    
        this.readcnt = entity.getReadcnt();
        this.regdate = entity.getRegdate();

	}
}
