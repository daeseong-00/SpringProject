package com.mnu.sample.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.mnu.sample.entity.PdsEntity;

//JpaRepository 인터페이스를 상속해서 사용자 인터페이스 생성
public interface PdsRepository extends JpaRepository<PdsEntity, Integer> {
	//count() // 카운트
	//findAll()	//전체목록
	//save(entity)// 등록, 수정(update)
	//findById()//기본키를 이용한 검색
	//delete()//삭제
	
	
}