package com.cp.board.contorller;

import com.cp.board.dto.BoardDTO;
import com.cp.board.dto.ResponseDTO;
import com.cp.board.dto.SearchDTO;
import com.cp.board.dto.WriteRequestDTO;
import com.cp.board.model.Board;
import com.cp.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/boards")
public class BoardController {

    final private BoardService boardService;

    @GetMapping
    public ResponseDTO getList(@PageableDefault Pageable pageable){
        return ResponseDTO.builder()
                .data(this.boardService.getList(pageable))
                .build();
    }


    @PostMapping
    public ResponseDTO write(@Valid @RequestBody BoardDTO boardDTO){
        return ResponseDTO.builder()
                .data(this.boardService.write(boardDTO))
                .build();
    }

    @GetMapping("/search")
    public ResponseDTO search(@Valid @RequestBody SearchDTO searchDTO, @PageableDefault Pageable pageable){
        return ResponseDTO.builder()
                .data(this.boardService.search(searchDTO, pageable))
                .build();
    }


}
