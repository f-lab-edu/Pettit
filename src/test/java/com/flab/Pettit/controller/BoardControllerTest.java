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
import org.springframework.http.*;
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

    /**
     모든 게시물 조회 테스트 코드
     Status 일치 여부와 Body 길이가 0L보다 큰지 확인
     모든 게시물을 boardRepository에서 가져와 List로 담은 다음 각각 isEqualTo로 테스트시 넣어준 값과 일치하는지를 확인한다.
     **/
    @Test
    public void findAll() throws Exception {
        String url = "http://localhost:" + port + "/api/post";

        ResponseEntity<String> responseEntity = testRestTemplate.getForEntity(url, String.class);
        System.out.println("responseEntity.getBody() = " + responseEntity.getBody());

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).contains("id");
        assertThat(responseEntity.getBody()).contains("title");
        assertThat(responseEntity.getBody()).contains("writer");
        assertThat(responseEntity.getBody()).contains("content");

    }

    /**
    게시물 저장에 관한 테스트 코드
    Status 일치 여부와 Body 길이가 0L보다 큰지 확인
    모든 게시물을 boardRepository에서 가져와 List로 담은 다음 각각 isEqualTo로 테스트시 넣어준 값과 일치하는지를 확인한다.
    **/
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
    /**
    게시물 상세 보기 테스트 코드
    id 값을 기준으로 id, title, content, writer를 담고 있는지 확인한다.
    **/
    @Test
    public void findById() throws Exception {
        Long id = 3L;

        String url = "http://localhost:" + port + "/api/post/" + id;
        ResponseEntity<String> responseEntity = testRestTemplate.getForEntity(url, String.class);


        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).contains("id");
        assertThat(responseEntity.getBody()).contains("title");
        assertThat(responseEntity.getBody()).contains("content");
        assertThat(responseEntity.getBody()).contains("writer");
    }
    /**
    특정 게시물 삭제
    **/
    @Test
    public void delete() throws Exception{
        String title = "삭제테스트";
        String writer = "홍기대";
        String content = "삭제내용";

        BoardEntity saveBoard = boardRepository.save(BoardEntity.builder()
                .title(title)
                .content(content)
                .writer(writer)
                .build());

        Long boardId = saveBoard.getId();

        String url = "http://localhost:" + port + "/api/post/" + boardId;

        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<Object> httpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<Long> responseEntity = testRestTemplate.exchange(url, HttpMethod.DELETE, httpEntity, Long.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

    }



}