package com.flab.Pettit.dto;

import com.flab.Pettit.domain.entity.BoardEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardSaveRequestDto {
    private Long id;
    private String writer;
    private String title;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;


    public BoardEntity toEntity(){
        BoardEntity build = BoardEntity.builder()
                .writer(writer)
                .title(title)
                .content(content)
                .build();
        return build;
    }

    @Builder
    public BoardSaveRequestDto(String writer, String title, String content, LocalDateTime createDate, LocalDateTime modifiedDate) {

        this.writer = writer;
        this.title = title;
        this.content = content;
    }
}
