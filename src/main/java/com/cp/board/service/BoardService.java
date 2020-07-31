package com.cp.board.service;

import com.cp.board.dto.BoardDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardService {
    List<BoardDTO> getList(Pageable p);
}
