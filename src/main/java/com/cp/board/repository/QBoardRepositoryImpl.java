package com.cp.board.repository;

import com.cp.board.dto.BoardDTO;
import com.cp.board.dto.SearchDTO;
import com.cp.board.model.Board;
import com.cp.board.model.QBoard;
import com.google.common.base.Strings;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RequiredArgsConstructor
public class QBoardRepositoryImpl implements  QBoardRepository{
    final private JPAQueryFactory jPAQueryFactory;

    @Override
    public List<BoardDTO> search(SearchDTO searchDTO, Pageable pageRequest) {

        QBoard board = QBoard.board;

        JPAQuery<BoardDTO> query = jPAQueryFactory.select(
                Projections.fields(BoardDTO.class,
                        board.title,
                        board.name,
                        board.content,
                        board.createdAt,
                        board.viewCount,
                        board.updatedAt
                        ))
                .from(board)
                .offset(pageRequest.getOffset())
                .limit(pageRequest.getPageSize())
                ;

        if(!Strings.isNullOrEmpty(searchDTO.getName())){
            query.where(board.name.contains(searchDTO.getName()));
        }

        if(!Strings.isNullOrEmpty(searchDTO.getTitle())){
            query.where(board.title.contains(searchDTO.getTitle()));
        }

        if(!Strings.isNullOrEmpty(searchDTO.getContent())){
            query.where(board.content.contains(searchDTO.getContent()));
        }
        if(searchDTO.getStartDate() != null && searchDTO.getEndDate() != null){
            query.where(board.createdAt.between(searchDTO.getStartDate(), searchDTO.getEndDate()));
        }


        return query.fetch();
    }

    @Override
    public long searchCount(SearchDTO searchDTO, Pageable pageRequest) {
        QBoard board = QBoard.board;

        JPAQuery<Board> query = jPAQueryFactory
                .selectFrom(board)
                ;

        if(!Strings.isNullOrEmpty(searchDTO.getName())){
            query.where(board.name.contains(searchDTO.getName()));
        }

        if(!Strings.isNullOrEmpty(searchDTO.getTitle())){
            query.where(board.title.contains(searchDTO.getTitle()));
        }

        if(!Strings.isNullOrEmpty(searchDTO.getContent())){
            query.where(board.content.contains(searchDTO.getContent()));
        }
        if(searchDTO.getStartDate() != null && searchDTO.getEndDate() != null){
            query.where(board.createdAt.between(searchDTO.getStartDate(), searchDTO.getEndDate()));
        }

        return query.fetchCount();
    }
}
