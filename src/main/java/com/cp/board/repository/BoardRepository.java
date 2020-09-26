package com.cp.board.repository;

import com.cp.board.dto.BoardDTO;
import com.cp.board.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long>, QBoardRepository, QuerydslPredicateExecutor<Board> {
}
