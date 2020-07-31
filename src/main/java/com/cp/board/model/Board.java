package com.cp.board.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name="tb_boards")
@NoArgsConstructor
public class Board extends Auditing{
    private String title;
    private String content;
    private String name;
    private long viewCount;

}
