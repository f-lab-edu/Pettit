package com.flab.Pettit.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;


import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("/posts 요청시 Test를 출력한다.")
    void test() throws Exception {
        //글 제목
        //글 내용

        // expected
       mockMvc.perform(MockMvcRequestBuilders.post("/api/posts")
                       .contentType(MediaType.APPLICATION_JSON)
                       .content("{\"title\": \"제목임\", \"content\": \"내용임\"}"))


               .andExpect(status().isOk())
               .andExpect(content().string("Test"))
               .andDo(print());

    }
    @Test
    @DisplayName("/posts 요청시 title값은 필수.")
    void test2() throws Exception {
        //글 제목
        //글 내용

        // expected
        mockMvc.perform(MockMvcRequestBuilders.post("/api/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": \"null\", \"content\": \"내용임\"}"))


                .andExpect(status().isOk())
                .andExpect(content().string("Test"))
                .andDo(print());

    }

}