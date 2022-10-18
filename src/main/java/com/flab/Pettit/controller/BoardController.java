package com.flab.Pettit.controller;

import com.flab.Pettit.dto.BoardDto;
import com.flab.Pettit.service.BoardService;
import io.swagger.annotations.Api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Api(tags = {"게시글"})
@Controller
@RequestMapping("/api")

public class BoardController {
    private final BoardService boardService;



    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }
    @GetMapping("/")
    public String list() {
        return "board/list.html";
    }
    @GetMapping("post")
    public String write(){
        return "board/write.html";
    }

    @PostMapping("/post")
    public String write(BoardDto boardDto){
        boardService.savePost(boardDto);
        return "redirect:/";
    }



}
