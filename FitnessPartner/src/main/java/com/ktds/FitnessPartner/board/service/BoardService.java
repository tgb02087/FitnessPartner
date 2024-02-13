package com.ktds.FitnessPartner.board.service;

import com.ktds.FitnessPartner.board.dto.BoardDto;
import com.ktds.FitnessPartner.board.entity.Board;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BoardService {
    void save(final BoardDto boardDTO);

    List<Board> findAll();

    Board findById(final Long id);

    String upload(final MultipartFile multipartFile) throws IOException;
}
