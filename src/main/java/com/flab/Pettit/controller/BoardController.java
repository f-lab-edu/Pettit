package com.flab.Pettit.controller;

import com.flab.Pettit.dto.BoardDto;
import com.flab.Pettit.service.BoardService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Api(tags = {"게시글"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")

public class BoardController {
    private final BoardService boardService;

    @GetMapping("/")
    public String list() {
        return "board/list.html";
    }
    @GetMapping("/post")
    public String write(){
        return "board/write.html";
    }

    @PostMapping(value = "/post", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Long> write(@RequestBody BoardDto boardDto) throws Exception {
        Long savedBoardSeq = boardService.savePost(boardDto);
        return new ResponseEntity<Long>(savedBoardSeq, HttpStatus.CREATED);
    }



}
