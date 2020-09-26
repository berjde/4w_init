package com.cp.board.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class SearchDTO {
    private String title;
    private String content;
    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
