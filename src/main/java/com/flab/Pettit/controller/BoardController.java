package com.flab.Pettit.controller;

import com.flab.Pettit.dto.BoardDto;
import com.flab.Pettit.service.BoardService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api(tags = {"게시글"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")

public class BoardController {
    private final BoardService boardService;
 // 모든 게시물 조회
    @GetMapping(value = "/post", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<BoardDto>> findAll() {
        List<BoardDto> boardDtoList = boardService.findAll();
        return new ResponseEntity<List<BoardDto>>(boardDtoList, HttpStatus.OK);
    }
 // 특정 게시물 조회
    @GetMapping(value = "/post/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<BoardDto> findById(@PathVariable("id") Long id){
        BoardDto boardDto = boardService.findById(id);
        return new ResponseEntity<BoardDto>(boardDto, HttpStatus.OK);
    }
 // 게시글 작성
    @PostMapping(value = "/post", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Long> write(@RequestBody BoardDto boardDto) throws Exception {
        Long savedBoardSeq = boardService.savePost(boardDto);
        return new ResponseEntity<Long>(savedBoardSeq, HttpStatus.CREATED);
    }
 // 특정 게시글 삭제
    @DeleteMapping(value = "/post/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Long> delete(@PathVariable("id") Long id){
        boardService.deletePost(id);
        return new ResponseEntity<Long>(id, HttpStatus.NO_CONTENT);
    }
 // 특정 게시글 수정



}
