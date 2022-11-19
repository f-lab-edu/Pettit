package com.flab.Pettit.dto;


import com.flab.Pettit.domain.board.Board;
import lombok.Getter;

@Getter
public class BoardResponseDto {

    private String title;
    private String writer;
    private String content;

    public BoardResponseDto(Board boardEntity) {
        this.title = boardEntity.getTitle();
        this.writer = boardEntity.getWriter();
        this.content = boardEntity.getContent();
    }
}
