package com.cp.board.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name="tb_boards")
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Board extends Auditing{
    @Size(min = 10, max = 20, message = "제목을 입력하세요")
    private String title;
    private String content;
    private String name;
    private long viewCount;

}
