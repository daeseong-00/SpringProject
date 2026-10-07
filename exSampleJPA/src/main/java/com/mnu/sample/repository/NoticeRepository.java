package com.mnu.sample.repository;


import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.mnu.sample.entity.NoticeEntity;



public interface NoticeRepository extends JpaRepository<NoticeEntity, Integer> {
	//count()//카운트
	//findAll()//전체목록
	//save(entity)//등록, 수정(update)
	//findById()//기본키를 이용한 검색
	//delete()//삭제

	//사용자 정의 메소드(1. 쿼리 메소드 / 2. @Query 어노테이션)
	//1. 삭제(id, pass)
	@Transactional
	@Modifying
	@Query("delete from NoticeEntity notice where notice.idx= :idx")
	int noticeDelete(@Param("idx") int idx);
	
	//idx에 해당하는 글의 조회수 증가
	@Transactional
	@Modifying
	@Query("update NoticeEntity notice set notice.readcnt=notice.readcnt+1 where notice.idx = :idx")
	void noticeHits(@Param("idx") int idx);
	
	
	//검색(이름, 제목, 내용) 카운트
	long countByAdidContaining(String keyword);
	//(adid like '%keyword%')
	long countBySubjectContaining(String keyword);
	long countByContentsContaining(String keyword);
	
	//검색 목록
	List<NoticeEntity> findByAdidContaining(String keyword);
	//(adid like '%keyword%')
	List<NoticeEntity> findBySubjectContaining(String keyword);
	List<NoticeEntity> findByContentsContaining(String keyword);
	
	//검색 목록(idx 기준 내림차순)
	List<NoticeEntity> findByAdidContainingOrderByIdxDesc(String keyword);
	List<NoticeEntity> findBySubjectContainingOrderByIdxDesc(String keyword);
	List<NoticeEntity> findByContentsContainingOrderByIdxDesc(String keyword);
	
	//페이지 인덱싱
	Page<NoticeEntity> findByAdidContainingOrderByIdxDesc(String keyword, Pageable pageable);
	Page<NoticeEntity> findBySubjectContainingOrderByIdxDesc(String keyword, Pageable pageable);
	Page<NoticeEntity> findByContentsContainingOrderByIdxDesc(String keyword, Pageable pageable);
	
	
	//@Query 이용한 검색 + Page
	@Query("select notice from NoticeEntity notice "
			+ " where (:search='adid' and notice.adid like %:key%) "
			+ " or (:search='subject' and notice.subject like %:key%) "
			+ " or (:search='contents' and notice.contents like %:key%) "
			+ " order by notice.idx desc")
	Page<NoticeEntity> noticeListSearchPage(@Param("search") String search, @Param("key") String key, 
			Pageable pageable);
	
}
