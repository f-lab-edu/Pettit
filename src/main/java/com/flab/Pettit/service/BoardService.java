package com.flab.Pettit.service;

import com.flab.Pettit.advice.exception.BoardIdNotFoundException;
import com.flab.Pettit.domain.board.Board;
import com.flab.Pettit.dto.BoardResponseDto;
import com.flab.Pettit.dto.BoardSaveRequestDto;
import com.flab.Pettit.dto.BoardUpdateRequestDto;
import com.flab.Pettit.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 게시판 작성, 수정, 삭제, 조회 기능을 담당하는 서비스 로직
 * @author  홍기대
 * @since   1.0
 **/
@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    /**
     * 게시물 작성
     * @param boardDto
     * @return 생성된 게시물의 id값
     **/
    @Transactional
    public Long savePost(BoardSaveRequestDto boardDto) {
        return boardRepository.save(boardDto.toEntity()).getId();
    }
    /**
     * 모든 게시물 조회
     * @return 모든 게시물 리스트
     * **/
    @Transactional(readOnly = true)
    public List<BoardResponseDto> findAll() {
        return boardRepository.findAll()
                .stream()
                .map(BoardResponseDto::new)
                .collect(Collectors.toList());
    }
    /**
     * 특정 게시물 조회
     * @param id 찾고자 하는 게시물의 id
     * @return 찾은 게시물
     **/
    @Transactional(readOnly = true)
    public BoardResponseDto findById(Long id) {
        Board boardEntity = boardRepository.findById(id).orElseThrow(BoardIdNotFoundException::new);
        return new BoardResponseDto(boardEntity);
    }
    /**
     * 특정 게시물 삭제
     * @param id 지우고자 하는 게시물의 id
     **/
    @Transactional
    public void deletePost(Long id) {
        Board boardEntity = boardRepository.findById(id)
                .orElseThrow(BoardIdNotFoundException::new);
        boardRepository.delete(boardEntity);
    }
    /**
     * 특정 게시물 수정
     * @param id 수정하고자 하는 게시물 id
     * @param boardUpdateRequestDto 수정하고자 하는 내용
     * @return 변경된 게시물
     **/
    @Transactional
    public Board update(Long id, BoardUpdateRequestDto boardUpdateRequestDto) {
        Board boardEntity = boardRepository.findById(id)
                .orElseThrow(BoardIdNotFoundException::new);
        boardEntity.update(boardUpdateRequestDto.getTitle(), boardUpdateRequestDto.getContent());
        return boardEntity;
    }
}
