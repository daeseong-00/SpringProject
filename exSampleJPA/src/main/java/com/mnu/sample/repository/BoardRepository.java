package com.mnu.sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mnu.sample.entity.BoardEntity;

public interface BoardRepository extends JpaRepository<BoardEntity, Integer> {
	

}
