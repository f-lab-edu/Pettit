package com.flab.Pettit.dto;


import com.flab.Pettit.domain.board.Board;
import lombok.Getter;

@Getter
public class BoardResponseDto {

    private Long id;
    private String title;
    private String writer;
    private String content;

    public BoardResponseDto(Board boardEntity) {
        this.id = boardEntity.getId();
        this.title = boardEntity.getTitle();
        this.writer = boardEntity.getWriter();
        this.content = boardEntity.getContent();
    }
}
