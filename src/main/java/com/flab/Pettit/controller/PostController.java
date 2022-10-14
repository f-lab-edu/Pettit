package com.flab.Pettit.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"초기페이지"})
@RestController
@RequestMapping("/api")
public class PostController {
    // localhost:8281/api/posts에 접속시 Hello World가 출력 된다.
    @GetMapping("/posts")
    public String get(){
        return "Hello World";
    }
}
