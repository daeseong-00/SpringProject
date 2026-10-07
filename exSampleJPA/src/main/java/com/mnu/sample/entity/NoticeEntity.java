package com.mnu.sample.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="tbl_notice")
@NoArgsConstructor
@Getter
public class NoticeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tbl_notice_seq_idx_GENERATOR")
	@SequenceGenerator(name="tbl_notice_seq_idx_GENERATOR", sequenceName = "tbl_notice_seq_idx", initialValue=1, allocationSize=1)
	//MySQL일 경우
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private int idx;
	private String adid;
	private String subject;
	private String contents;
	private int readcnt;
	private LocalDateTime regdate = LocalDateTime.now();

	
	@Builder
	public NoticeEntity(String adid, String subject, String contents) {
		this.adid=adid;
		this.subject=subject;
		this.contents=contents;
	}
	

}
