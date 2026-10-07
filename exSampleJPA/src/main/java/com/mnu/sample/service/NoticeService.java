package com.mnu.sample.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import com.mnu.sample.dto.NoticeResponseDTO;
import com.mnu.sample.entity.NoticeEntity;
import com.mnu.sample.repository.NoticeRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor //Bean 주입
public class NoticeService {
	private final NoticeRepository noticeRepository;
	
	
	//카운트 (전체 게시글 수)
	@Transactional
	public long noticeCount() {
		return noticeRepository.count();
	}
	/*
	//전체목록(페이지 처리 없음)
	@Transactional
	public List<NoticeResponseDTO> noticeList(){
		return noticeRepository.findAll()
				.stream()
				.sorted(Comparator.comparing(NoticeEntity::getIdx).reversed())
				.map(NoticeResponseDTO::new)
				.collect(Collectors.toList());
		//NoticeRepository 결과로 넘어온 NoticeEntity의 Stream을 map을 통해 NoticeResponseDTO로 변환
		//-> List로 반환하는 메서드
	}

	*/
	
	//전체목록(페이지 처리)
		@Transactional
		public Page<NoticeResponseDTO> noticeList(Pageable pageable){
			Pageable sortedPageable = PageRequest.of(
				pageable.getPageNumber(),
				pageable.getPageSize(),
				Sort.by(Sort.Direction.DESC,"idx")
					);
			Page<NoticeEntity> page;
			page = noticeRepository.findAll(sortedPageable);
			return page.map(NoticeResponseDTO::new);
		}
		
		//전체목록(페이지 처리)
		@Transactional
		public Page<NoticeResponseDTO> noticeListSearchPage(String search, String key, Pageable pageable){
			Pageable sortedPageable = PageRequest.of(
				pageable.getPageNumber(),
				pageable.getPageSize(),
				Sort.by(Sort.Direction.DESC,"idx")
					);
			Page<NoticeEntity> page;
			if(key != null && !key.equals("")) {
				//검색 O
				page = noticeRepository.noticeListSearchPage(search, key, pageable);
			}else {
			page = noticeRepository.findAll(sortedPageable);
			}
			return page.map(NoticeResponseDTO::new);
				}
	//상세보기(view)
	@Transactional
	public NoticeResponseDTO noticeView(int idx) {
		//조회수 증가
		noticeRepository.noticeHits(idx);
		
		NoticeEntity noticeEntity = noticeRepository.findById(idx)
				.orElseThrow(()->new IllegalArgumentException("idx 없음"));
		
		NoticeResponseDTO notice = new NoticeResponseDTO(noticeEntity);
		return notice;
		
	}
	
	//삭제
	@Transactional
	public int noticeDelete(int idx) {
		return noticeRepository.noticeDelete(idx);
	}
	
		
	
	//조건에 맞는 글 수 카운트
	@Transactional
	public long noticeCountSearch(String search, String key) {
		switch(search) {
		case "adid":
			return noticeRepository.countByAdidContaining(key);
		case "subject":
			return noticeRepository.countBySubjectContaining(key);
		case "contents":
			return noticeRepository.countByContentsContaining(key);
		default:
			return 0;
		}
	}
	
	//조건에 맞는 게시글 목록
	@Transactional
	public List<NoticeResponseDTO> noticeListSearch(String search, String key){
		switch(search) {
		case "adid":
			return noticeRepository.findByAdidContainingOrderByIdxDesc(key)
					.stream()
					.map(NoticeResponseDTO::new)
					.toList();
		case "subject":
			return noticeRepository.findBySubjectContainingOrderByIdxDesc(key)
					.stream()
					.map(NoticeResponseDTO::new)
					.toList();
		case "contents":
			return noticeRepository.findByContentsContainingOrderByIdxDesc(key)
					.stream()
					.map(NoticeResponseDTO::new)
					.toList();
			//NoticeRepository 결과로 넘어온 Entity의 stream을 map을 통해 list로 변환
		default:
			return null;
		}
	}
}
