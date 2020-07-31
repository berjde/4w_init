package com.cp.board.service;

import com.cp.board.dto.BoardDTO;
import com.cp.board.exception.ApiException;
import com.cp.board.model.ErrorCode;
import com.cp.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    final private BoardRepository boardRepository;
    final private ModelMapper modelMapper;

    @Override
    public List<BoardDTO> getList(Pageable p) {
        List<BoardDTO> list = boardRepository.findAll(p).stream()
                .map(b -> modelMapper.map(b, BoardDTO.class))
                .collect(Collectors.toList());

        if(list.size() == 0) throw new ApiException(ErrorCode.NOT_FOUND_DATA);

        return list;

    }
}
