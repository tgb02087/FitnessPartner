package com.ktds.FitnessPartner.board.controller;

import com.ktds.FitnessPartner.board.entity.Board;
import com.ktds.FitnessPartner.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    private final String uploadDir = "C:\\Users\\KTDS\\Desktop\\Project\\FitnessPartner\\FitnessPartner\\src\\main\\resources\\static\\upload\\";

    @GetMapping("/food_share")
    public String food_share(Model model) {
        List<Board> boards = boardService.findAll();
        model.addAttribute("boards", boards);
        model.addAttribute("pageNum", 3);
        return "food_share";
    }

    @GetMapping("/create")
    public String createPage(Model model) {
        model.addAttribute("pageNum", 3);
        return "food_share_create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Board board) {
        boardService.save(board);
        return "redirect:/board/food_share";
    }

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) throws IOException {
        System.out.println("multi");
        if (!file.isEmpty()) {
            String filename = file.getOriginalFilename();
//            log.info("file.getOriginalFilename = {}", filename);

            String fullPath = uploadDir + filename;
            file.transferTo(new File(fullPath));
        }

        return "food_share_create";
    }
}
