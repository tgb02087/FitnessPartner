package com.ktds.FitnessPartner.board.repository;

import com.ktds.FitnessPartner.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
