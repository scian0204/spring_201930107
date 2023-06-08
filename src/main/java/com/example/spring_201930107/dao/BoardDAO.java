package com.example.spring_201930107.dao;

import com.example.spring_201930107.dto.BoardResponseDto;
import com.example.spring_201930107.entity.Board;

import java.util.Arrays;
import java.util.List;

public interface BoardDAO {
    Board updateBoard(Board board, String token);

    Board writeBoard(Board board, String token);

    void deleteBoard(Long id, String token);

    List<Board> getAll();

    List<Board> getAllOrderByCreateAt();

    List<Board> getByUserId(String userId);

    Board getById(Long id);
}
