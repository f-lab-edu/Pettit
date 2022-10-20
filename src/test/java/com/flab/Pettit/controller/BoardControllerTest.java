package com.flab.Pettit.controller;

import com.flab.Pettit.domain.entity.BoardEntity;
import com.flab.Pettit.dto.BoardDto;
import com.flab.Pettit.repository.BoardRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BoardControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private BoardRepository boardRepository;


    @Test
    public void save() throws Exception{
        String writer = "홍기대";
        String title = "게시글 제목 test";
        String content = "게시글 컨텐츠 test";

        BoardDto boardDto = BoardDto.builder()
                .title(title)
                .writer(writer)
                .content(content)
                .build();
        String url = "http://localhost:" + port + "/api/post";

        ResponseEntity<Long> responseEntity = testRestTemplate.postForEntity(url, boardDto, Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<BoardEntity> boardList = boardRepository.findAll();
        BoardEntity boardEntity = boardList.get(boardList.size() - 1);
        assertThat(boardEntity.getTitle()).isEqualTo(title);
        assertThat(boardEntity.getContent()).isEqualTo(content);
        assertThat(boardEntity.getWriter()).isEqualTo(writer);
    }



}