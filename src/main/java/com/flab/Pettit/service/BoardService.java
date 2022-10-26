package com.flab.Pettit.service;

import com.flab.Pettit.domain.entity.BoardEntity;
import com.flab.Pettit.dto.BoardResponseDto;
import com.flab.Pettit.dto.BoardSaveRequestDto;
import com.flab.Pettit.dto.BoardUpdateRequestDto;
import com.flab.Pettit.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BoardService {
    /**
    final키워드를 사용한 인스턴스들만 Bean객체로 등록하게됨
    final 키워두를 사용하지 않으면 테스트 코드에서 NullPointerException이 발생함
    **/
    private final BoardRepository boardRepository;

    /**
    savePost는 Ropository를 통해 실제로 데이터를 저장하게 된다.
    repository의 save는 JpaRepository -> PagingAndSortingRepository -> CrudRepository의 인터페이스이다
    **/
    @Transactional
    public Long savePost(BoardSaveRequestDto boardDto) {
        return boardRepository.save(boardDto.toEntity()).getId();
    }
    /**
    DB에 저장되어 있는 전체 데이터를 조회한다.
    **/
    @Transactional(readOnly = true)
    public List<BoardResponseDto> findAll() {
        return boardRepository.findAll()
                .stream()
                .map(BoardResponseDto::new)
                .collect(Collectors.toList());
    }
    /**
    게시글 - 상세 조회
    **/
    @Transactional(readOnly = true)
    public BoardResponseDto findById(Long id) throws IllegalArgumentException{
        BoardEntity boardEntity = boardRepository.findById(id).orElseThrow(() -> new IllegalAccessError("[id=" + id + "] 해당 게시글이 존재하지 않습니다."));
        return new BoardResponseDto(boardEntity);
    }
    /**
    게시글 - 삭제 기능
    **/
    @Transactional
    public void deletePost(Long id) throws IllegalArgumentException{
        BoardEntity boardEntity = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalAccessError("[id=" + id + "] 해당 게시글이 존재하지 않습니다."));
        boardRepository.delete(boardEntity);
    }
    /**
    게시글 - 수정 기능
    **/
    @Transactional
    public Long update(Long id, BoardUpdateRequestDto boardUpdateRequestDto) {
        BoardEntity boardEntity = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalAccessError("[boardSeq=" + id + "] 해당 게시글이 존재하지 않습니다."));
        boardEntity.update(boardUpdateRequestDto.getTitle(), boardUpdateRequestDto.getContent());
        return id;
    }
}
