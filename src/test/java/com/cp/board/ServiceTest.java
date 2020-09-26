package com.cp.board;

import com.cp.board.dto.BoardDTO;
import com.cp.board.dto.SearchDTO;
import com.cp.board.model.Board;
import com.cp.board.service.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;


@SpringBootTest
public class ServiceTest {
    @Autowired
    private BoardService boardService;

    @Test
    void 리스트() throws Exception{
        SearchDTO searchDTO = SearchDTO.builder()
                //.name("사용자")
                //.content("tttt")
                .startDate(LocalDateTime.now().minusDays(1))
                .endDate(LocalDateTime.now())
                .build();
        PageRequest pageRequest = PageRequest.of(0, 20);
        Page<BoardDTO> boardDTOS = this.boardService.search(searchDTO, pageRequest);

        boardDTOS.get().forEach(b -> System.out.println(b));

    }

    @Test
    void 작성() throws Exception {
        BoardDTO boardDTO = BoardDTO.builder()
                .title("트")
                .content("내용무")
                .name("정진용")
                .build();

        this.boardService.write(boardDTO);
    }

    @Test
    void 삭제() throws Exception {
        BoardDTO boardDTO = BoardDTO.builder()
                .id(1L)
                .build();

        this.boardService.delete(boardDTO);
    }

    @Test
    void 읽기() throws Exception {
        BoardDTO boardDTO = BoardDTO.builder()
                .id(3L)
                .build();
        BoardDTO result = this.boardService.read(boardDTO);

        System.out.println(result);
    }

    @Test
    void 조회수_올리기() throws Exception{
        BoardDTO boardDTO = BoardDTO.builder()
                .id(3L)
                .build();

        this.boardService.updateViewCount(boardDTO);


    }
}
