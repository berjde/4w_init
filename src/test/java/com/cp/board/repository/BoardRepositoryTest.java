package com.cp.board.repository;

import com.cp.board.model.QBoard;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardRepositoryTest {
    @Autowired
    private BoardRepository boardRepository;

    @Test
    void 리스트_쿼리디에스엘(){
        QBoard qBoard = QBoard.board;

        BooleanExpression query = qBoard.name.contains("test")
                .and(qBoard.createdAt.between(LocalDateTime.now(), LocalDateTime.now()));

        if("test".equals("test")){
            query.and(qBoard.name.notEqualsIgnoreCase("test"));
        }

        PageRequest pageRequest = PageRequest.of(0, 10);
        long count = this.boardRepository.count(query);
        this.boardRepository.findAll(query, pageRequest);
    }
}