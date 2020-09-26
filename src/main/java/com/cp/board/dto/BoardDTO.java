package com.cp.board.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class BoardDTO extends BaseDTO {
    @Size(min = 10, max = 20, message = "제목을 입력하세요")
    private String title;
    @NotNull(message = "내용을 입력하세요")
    private String content;
    @NotNull
    @Size(min=2, max = 10, message = "2글자 이상 10글자 이하")
    @Pattern(regexp = "/^[aZ]/", message = "이름이 틀렸어")
    private String name;
    @Email
    private String email;
    @Digits(integer = 1, fraction = 0)
    private int level;
    private long viewCount;


    @Builder
    @QueryProjection
    public BoardDTO(long id, LocalDateTime createdAt, LocalDateTime updatedAt, boolean isDeleted,@Size(min = 10, max = 20, message = "제목을 입력하세요") String title, String content, String name, long viewCount) {
        super(id, createdAt, updatedAt, isDeleted);
        this.title = title;
        this.content = content;
        this.name = name;
        this.viewCount = viewCount;
    }
}
