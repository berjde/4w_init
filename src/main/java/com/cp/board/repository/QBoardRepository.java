package com.cp.board.repository;

import com.cp.board.dto.BoardDTO;
import com.cp.board.dto.SearchDTO;
import org.springframework.cloud.sleuth.annotation.NewSpan;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface QBoardRepository {
    List<BoardDTO> search(SearchDTO searchDTO, Pageable pageRequest);

    long searchCount(SearchDTO searchDTO, Pageable pageRequest);
}
