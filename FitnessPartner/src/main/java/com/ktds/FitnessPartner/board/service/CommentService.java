package com.ktds.FitnessPartner.board.service;

import com.ktds.FitnessPartner.board.dto.CommentDto;
import com.ktds.FitnessPartner.board.entity.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> findAllByBoardId(final CommentDto commentDTO);

    Comment save(final CommentDto commentDTO);
}
