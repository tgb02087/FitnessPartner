package com.ktds.FitnessPartner.board.service;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.ktds.FitnessPartner.board.dto.BoardDto;
import com.ktds.FitnessPartner.board.entity.Board;
import com.ktds.FitnessPartner.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;
    private final Bucket bucket;
    @Override
    @Transactional
    public void save(BoardDto boardDTO) {
        Board board = Board.builder()
                .title(boardDTO.getTitle())
                .content(boardDTO.getContent())
                .image(boardDTO.getImgage())
                .createTime(LocalDateTime.now())
                .build();
        boardRepository.save(board);
    }

    @Override
    public List<Board> findAll() {
        return boardRepository.findAll();
    }

    @Override
    public Board findById(final Long id) {
        Optional<Board> optionalBoard = boardRepository.findById(id);
        return optionalBoard.orElse(null);
    }

    @Override
    @Transactional
    public String upload(final MultipartFile multipartFile) throws IOException {
        Blob blob = bucket.create(multipartFile.getOriginalFilename(), multipartFile.getBytes());
        String[] urls = blob.getMediaLink().split("https://storage.googleapis.com/download/storage/v1/b/");
        return "https://firebasestorage.googleapis.com/v0/b/" + urls[1];
    }
}
