package com.ktds.FitnessPartner.board.service;

import com.ktds.FitnessPartner.board.dto.CommentDto;
import com.ktds.FitnessPartner.board.entity.Board;
import com.ktds.FitnessPartner.board.entity.Comment;
import com.ktds.FitnessPartner.board.repository.BoardRepository;
import com.ktds.FitnessPartner.board.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentServiceImpl implements CommentService {
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;
    @Override
    public List<Comment> findAllByBoardId(CommentDto commentDTO) {
        return commentRepository.findAllByBoardId(Board.builder().id(commentDTO.getBoardId()).build());
    }

    @Override
    @Transactional
    public Comment save(CommentDto commentDTO) {
        Board board = boardRepository.findById(commentDTO.getBoardId()).orElse(null);
        Comment comment = Comment.builder()
                .boardId(board)
                .contents(commentDTO.getComments())
                .createTime(LocalDateTime.now())
                .build();
        return commentRepository.save(comment);
    }
}
