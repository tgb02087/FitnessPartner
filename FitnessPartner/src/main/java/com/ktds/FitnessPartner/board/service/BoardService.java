package com.ktds.FitnessPartner.board.service;

import com.ktds.FitnessPartner.board.entity.Board;

import java.util.List;

public interface BoardService {
    void save(final Board board);

    List<Board> findAll();
}
