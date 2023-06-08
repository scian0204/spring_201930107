package com.example.spring_201930107.dto;

import com.example.spring_201930107.entity.Board;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardResponseDto {
    private long id;
    private String title;
    private String contents;
    private String userId;
    private String userName;
    private LocalDateTime createdAt;

    public BoardResponseDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.contents = board.getContents();
        this.userId = board.getUserId();
        this.userName = board.getUserName();
        this.createdAt = board.getCreatedAt();
    }
}
