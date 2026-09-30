package com.mnu.sample.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.mnu.sample.dto.BoardRequestDTO;
import com.mnu.sample.dto.BoardResponseDTO;
import com.mnu.sample.entity.BoardEntity;
import com.mnu.sample.repository.BoardRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor //Bean 주입
public class BoardService {
	private final BoardRepository boardRepository;
	
	//등록처리
	@Transactional
	public int boardWrite(BoardRequestDTO board) {
		return boardRepository.save(board.toEntity()).getIdx();
		//등록 후 등록된 idx 반환
	}
	//전체목록
	@Transactional
	public List<BoardResponseDTO> boardList(){
		return boardRepository.findAll()
				.stream()
				.sorted(Comparator.comparing(BoardEntity::getIdx).reversed())
				.map(BoardResponseDTO::new)
				.collect(Collectors.toList());
		//BoardRepository 결과로 넘어온 BoardEntity의 Stream을 map을 통해 BoardResponseDTO로 변환
		//-> List로 반환하는 메서드
	}
	
}
