package com.example.spring_201930107.service.impl;

import com.example.spring_201930107.dao.BoardDAO;
import com.example.spring_201930107.dto.BoardChangeDto;
import com.example.spring_201930107.dto.BoardRequestDto;
import com.example.spring_201930107.dto.BoardResponseDto;
import com.example.spring_201930107.entity.Board;
import com.example.spring_201930107.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardServiceImpl implements BoardService {
    @Autowired
    BoardDAO boardDAO;

    @Override
    public BoardResponseDto updateBoard(BoardChangeDto boardChangeDto, String token) {
        Board board = new Board();
        board.setId(boardChangeDto.getId());
        board.setTitle(boardChangeDto.getTitle());
        board.setContents(boardChangeDto.getContents());
        board.setUpdatedAt(LocalDateTime.now());
        return new BoardResponseDto(boardDAO.updateBoard(board, token));
    }

    @Override
    public BoardResponseDto writeBoard(BoardRequestDto boardRequestDto, String token) {
        Board board = new Board();
        board.setTitle(boardRequestDto.getTitle());
        board.setContents(boardRequestDto.getContents());
        board.setCreatedAt(LocalDateTime.now());
        board.setUpdatedAt(LocalDateTime.now());
        return new BoardResponseDto(boardDAO.writeBoard(board, token));
    }

    @Override
    public void deleteBoard(Long id, String token) {
        boardDAO.deleteBoard(id, token);
    }

    @Override
    public List<BoardResponseDto> getAll() {
        return boardDAO.getAll()
                .stream().map(BoardResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<BoardResponseDto> getAllOrderByCreateAt() {
        return boardDAO.getAllOrderByCreateAt()
                .stream().map(BoardResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<BoardResponseDto> getByUserId(String userId) {
        return boardDAO.getByUserId(userId)
                .stream().map(BoardResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public BoardResponseDto getById(Long id) {
        return new BoardResponseDto(boardDAO.getById(id));
    }
}
