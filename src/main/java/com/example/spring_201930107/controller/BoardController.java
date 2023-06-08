package com.example.spring_201930107.controller;

import com.example.spring_201930107.dto.BoardChangeDto;
import com.example.spring_201930107.dto.BoardRequestDto;
import com.example.spring_201930107.dto.BoardResponseDto;
import com.example.spring_201930107.service.BoardService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/board")
public class BoardController {
    @Autowired
    BoardService boardService;

    @Operation(summary = "게시글 수정 - 본인이 작성한 글만 수정 가능")
    @PutMapping("")
    public ResponseEntity<BoardResponseDto> updateBoard(@RequestBody BoardChangeDto boardChangeDto, HttpServletRequest request) {
        String token = request.getHeader("X-AUTH-TOKEN");
        return ResponseEntity.status(HttpStatus.OK).body(boardService.updateBoard(boardChangeDto, token));
    }
    @Operation(summary = "게시글 등록 - USER or ADMIN 권한이 있어야 등록 가능")
    @PostMapping("")
    public ResponseEntity<BoardResponseDto> writeBoard(@RequestBody BoardRequestDto boardRequestDto, HttpServletRequest request) {
        String token = request.getHeader("X-AUTH-TOKEN");
        return ResponseEntity.status(HttpStatus.OK).body(boardService.writeBoard(boardRequestDto, token));
    }
    @Operation(summary = "게시글 삭제 - 본인이 작성한 글만 삭제 가능")
    @DeleteMapping("")
    public ResponseEntity<String> updateBoard(@RequestParam Long id, HttpServletRequest request) {
        String token = request.getHeader("X-AUTH-TOKEN");
        boardService.deleteBoard(id, token);
        return ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제되었습니다.");
    }

    @Operation(summary = "게시판 리스트 - 누구나")
    @GetMapping("/list")
    public ResponseEntity<List<BoardResponseDto>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(boardService.getAll());
    }
    @Operation(summary = "게시글 리스트 - 작성일시 순 (내림차순) (누구나, 작성자 중복 등록 가능)")
    @GetMapping("/listOrderByCreateAt")
    public ResponseEntity<List<BoardResponseDto>> getAllOrderByCreateAt() {
        return ResponseEntity.status(HttpStatus.OK).body(boardService.getAllOrderByCreateAt());
    }
    @Operation(summary = "게시글 리스트 - 작성자를 통해 가져오기 (누구나, 작성자 중복 등록 가능)")
    @GetMapping("/byUserId")
    public ResponseEntity<List<BoardResponseDto>> getByUserId(@RequestParam String userId) {
        return ResponseEntity.status(HttpStatus.OK).body(boardService.getByUserId(userId));
    }
    @Operation(summary = "게시글 정보 - 아이디를 통해 가져오기, 누구나")
    @GetMapping("/")
    public ResponseEntity<BoardResponseDto> getById(@RequestParam Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(boardService.getById(id));
    }
}
