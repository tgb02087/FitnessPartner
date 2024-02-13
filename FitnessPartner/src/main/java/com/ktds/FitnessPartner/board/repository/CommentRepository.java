package com.ktds.FitnessPartner.board.repository;

import com.ktds.FitnessPartner.board.entity.Board;
import com.ktds.FitnessPartner.board.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByBoardId(final Board boardId);
}
