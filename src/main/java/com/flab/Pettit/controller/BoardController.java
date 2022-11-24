package com.flab.Pettit.controller;

import com.flab.Pettit.dto.BoardResponseDto;
import com.flab.Pettit.dto.BoardSaveRequestDto;
import com.flab.Pettit.dto.BoardUpdateRequestDto;
import com.flab.Pettit.dto.common.CommonResponse;
import com.flab.Pettit.dto.common.Msg;
import com.flab.Pettit.dto.common.StatusCode;
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
@RequestMapping("/api/posts")

public class BoardController {

    private final BoardService boardService;
    // 모든 게시물 조회
    @GetMapping
    public ResponseEntity<List<BoardResponseDto>> findAll() {
        return new ResponseEntity(CommonResponse.res(StatusCode.OK, Msg.SUCCESS_FIND_BOARD_ALL, boardService.findAll()), null, HttpStatus.OK);
    }
    // 특정 게시물 조회
    @GetMapping(value = "/{id}")
    public ResponseEntity findById(@PathVariable Long id){
        return new ResponseEntity(CommonResponse.res(StatusCode.OK, Msg.SUCCESS_FIND_BOARD, boardService.findById(id)), null, HttpStatus.OK);
    }
    // 게시글 작성
    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity write(@RequestBody BoardSaveRequestDto boardDto){
        return new ResponseEntity(CommonResponse.res(StatusCode.CREATED, Msg.SUCCESS_CREATE_BOARD, boardService.savePost(boardDto)), null, HttpStatus.CREATED);
    }
    // 특정 게시글 삭제
    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        boardService.deletePost(id);
        return new ResponseEntity(CommonResponse.res(StatusCode.NO_CONTENT, Msg.SUCCESS_DELETE_BOARD, id), null, HttpStatus.NO_CONTENT);
    }
    // 특정 게시글 수정
    @PutMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity update(@PathVariable Long id, @RequestBody BoardUpdateRequestDto boardUpdateRequestDto){
        return new ResponseEntity(CommonResponse.res(StatusCode.OK, Msg.SUCCESS_UPDATE_BOARD, boardService.update(id, boardUpdateRequestDto)), null, HttpStatus.OK);
    }



}
