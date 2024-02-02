package com.ktds.FitnessPartner.board.service;

import com.ktds.FitnessPartner.board.entity.Board;
import com.ktds.FitnessPartner.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardServiceImpl implements BoardService{
    private final BoardRepository boardRepository;
    @Override
    @Transactional
    public void save(Board board) {
        boardRepository.save(board);
    }

    @Override
    public List<Board> findAll() {
        return boardRepository.findAll();
    }
}
