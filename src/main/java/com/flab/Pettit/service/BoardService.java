package com.flab.Pettit.service;

import com.flab.Pettit.domain.entity.BoardEntity;
import com.flab.Pettit.dto.BoardDto;
import com.flab.Pettit.repository.BoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    /**
    DB에 저장되어 있는 전체 데이터를 조회한다.
    repository에서 모든 데이터를 가져와, 데이터만큼 반복하면서 BoardDto 타입의 List에 데이터를 파싱하여 집어넣고
    완성된 BoardDto 타입의 List를 리턴해준다.
    **/
    @Transactional(readOnly = true)
    public List<BoardDto> findAll(){
        List<BoardEntity> boards = boardRepository.findAll();
        List<BoardDto> boardDtoList = new ArrayList<>();

        for(BoardEntity boardEntity : boards){
            BoardDto boardDto = BoardDto.builder()
                    .id(boardEntity.getId())
                    .title(boardEntity.getTitle())
                    .content(boardEntity.getContent())
                    .writer(boardEntity.getWriter())
                    .createDate(boardEntity.getCreatDate())
                    .build();
            boardDtoList.add(boardDto);
        }
        return boardDtoList;
    }
    /**
    게시글 - 상세 조회
    **/
    @Transactional(readOnly = true)
    public BoardDto findById(Long id) {
        Optional<BoardEntity> boardWrapper = boardRepository.findById(id);
        BoardEntity boardEntity = boardWrapper.get();
        BoardDto boardDto = BoardDto.builder()
                .id(boardEntity.getId())
                .title(boardEntity.getTitle())
                .content(boardEntity.getContent())
                .writer(boardEntity.getWriter())
                .createDate(boardEntity.getCreatDate())
                .build();
        return boardDto;
    }
    /**
    게시글 - 삭제 기능
    **/
    @Transactional
    public void deletePost(Long id) {
        boardRepository.deleteById(id);

    }
}
