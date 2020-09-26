package com.cp.board.service;

import com.cp.board.dto.BoardDTO;
import com.cp.board.dto.SearchDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardService {
    List<BoardDTO> getList(Pageable p);

    int write(BoardDTO boardDTO);

    void delete(BoardDTO boardDTO);

    BoardDTO read(BoardDTO boardDTO);

    void updateViewCount(BoardDTO boardDTO);

    /**
     * 검색하기
     * @param searchDTO
     * @param pageRequest
     * @return
     */
    Page<BoardDTO> search(SearchDTO searchDTO, Pageable pageRequest);

}
