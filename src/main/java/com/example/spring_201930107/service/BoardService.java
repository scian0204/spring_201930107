package com.example.spring_201930107.service;

import com.example.spring_201930107.dto.BoardChangeDto;
import com.example.spring_201930107.dto.BoardRequestDto;
import com.example.spring_201930107.dto.BoardResponseDto;

import java.util.List;

public interface BoardService {
    BoardResponseDto updateBoard(BoardChangeDto boardChangeDto, String token);

    BoardResponseDto writeBoard(BoardRequestDto boardRequestDto, String token);

    void deleteBoard(Long id, String token);

    List<BoardResponseDto> getAll();

    List<BoardResponseDto> getAllOrderByCreateAt();

    List<BoardResponseDto> getByUserId(String userId);

    BoardResponseDto getById(Long id);
}
