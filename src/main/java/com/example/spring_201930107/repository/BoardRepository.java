package com.example.spring_201930107.repository;

import com.example.spring_201930107.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findAllByOrderByCreatedAtDesc();
    List<Board> findAllByUserId(String userId);
}
