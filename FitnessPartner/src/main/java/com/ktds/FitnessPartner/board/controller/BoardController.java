package com.ktds.FitnessPartner.board.controller;

import com.ktds.FitnessPartner.board.entity.Board;
import com.ktds.FitnessPartner.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/food_share")
    public String food_share(Model model) {
        List<Board> boards = boardService.findAll();
        model.addAttribute("boards", boards);
        model.addAttribute("pageNum", 3);
        return "food_share";
    }

    @GetMapping("/create")
    public String createPage() {
        return "food_share_create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Board board) {
        boardService.save(board);
        return "redirect:/board/food_share";
    }
}
