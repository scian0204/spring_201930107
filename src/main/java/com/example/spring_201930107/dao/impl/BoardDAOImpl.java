package com.example.spring_201930107.dao.impl;

import com.example.spring_201930107.config.security.JwtTokenProvider;
import com.example.spring_201930107.dao.BoardDAO;
import com.example.spring_201930107.dao.UserDAO;
import com.example.spring_201930107.entity.Board;
import com.example.spring_201930107.entity.User;
import com.example.spring_201930107.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BoardDAOImpl implements BoardDAO {
    @Autowired
    BoardRepository boardRepository;
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @Autowired
    UserDAO userDAO;

    @Override
    public Board updateBoard(Board board, String token) {
        board.setUserId(jwtTokenProvider.getUsername(token));
        Optional<Board> preBoardOpt = boardRepository.findById(board.getId());
        Board updatedBoard = null;
        if (preBoardOpt.isPresent()) {
            Board preBoard = preBoardOpt.get();
            if (preBoard.getUserId().equals(board.getUserId())) {
                board.setCreatedAt(preBoard.getCreatedAt());
                board.setUserName(preBoard.getUserName());
                updatedBoard = boardRepository.save(board);
            }
        }

        return updatedBoard;
    }

    @Override
    public Board writeBoard(Board board, String token) {
        board.setUserId(jwtTokenProvider.getUsername(token));
        User user = userDAO.loadUserByUsername(board.getUserId());
        board.setUserName(user.getName());
        return boardRepository.save(board);
    }

    @Override
    public boolean deleteBoard(Long id, String token) {
        Optional<Board> preBoardOpt = boardRepository.findById(id);
        boolean flag = false;
        if (preBoardOpt.isPresent()) {
            Board preBoard = preBoardOpt.get();
            if (preBoard.getUserId().equals(jwtTokenProvider.getUsername(token))) {
                boardRepository.delete(preBoard);
                flag = true;
            }
        }
        return flag;
    }

    @Override
    public List<Board> getAll() {
        return boardRepository.findAll();
    }

    @Override
    public List<Board> getAllOrderByCreateAt() {
        return boardRepository.findAllByOrderByCreatedAtDesc();
    }

    @Override
    public List<Board> getByUserId(String userId) {
        return boardRepository.findAllByUserId(userId);
    }

    @Override
    public Board getById(Long id) {
        return boardRepository.getReferenceById(id);
    }
}
