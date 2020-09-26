package com.cp.board.service;

import com.cp.board.dto.BoardDTO;
import com.cp.board.dto.SearchDTO;
import com.cp.board.exception.ApiException;
import com.cp.board.model.Board;
import com.cp.board.model.ErrorCode;
import com.cp.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
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

    @Override
    public int write(@Valid BoardDTO boardDTO) {
        Board board = this.modelMapper.map(boardDTO, Board.class);

        this.boardRepository.save(board);
        return 1;
    }

    @Override
    public void delete(BoardDTO boardDTO) {
        this.boardRepository.deleteById(boardDTO.getId());
    }

    @Override
    public BoardDTO read(BoardDTO boardDTO) {
        Optional<Board> board = this.boardRepository.findById(boardDTO.getId());

        return this.modelMapper.map(
                board.orElse(Board.builder().build())
                , BoardDTO.class);

    }

    @Override
    public void updateViewCount(BoardDTO boardDTO) {
        Board board = this.boardRepository.findById(boardDTO.getId())
                .orElseThrow(RuntimeException::new);

        board.setViewCount(board.getViewCount() + 1);

        this.boardRepository.save(board);

    }

    @Override
    public Page<BoardDTO> search(SearchDTO searchDTO, Pageable pageRequest) {
        Optional<Page<BoardDTO>> returnValue  = Optional.empty();
        List<BoardDTO> boardPage = this.boardRepository.search(searchDTO, pageRequest);
        long count =  this.boardRepository.searchCount(searchDTO, pageRequest);
        returnValue = Optional.ofNullable(new PageImpl<BoardDTO>(boardPage, pageRequest, count ));

        return returnValue.orElseThrow(RuntimeException::new);
    }
}
