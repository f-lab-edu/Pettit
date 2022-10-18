package com.flab.Pettit.service;

import com.flab.Pettit.dto.BoardDto;
import com.flab.Pettit.repository.BoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BoardService {
    private BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }
    /**
     savePost는 Ropository를 통해 실제로 데이터를 저장하게 된다.
     repository의 save는 JpaRepository -> PagingAndSortingRepository -> CrudRepository의 인터페이스이다
     **/
    @Transactional
    public Long savePost(BoardDto boardDto){
        return boardRepository.save(boardDto.toEntity()).getId();
    }
}
