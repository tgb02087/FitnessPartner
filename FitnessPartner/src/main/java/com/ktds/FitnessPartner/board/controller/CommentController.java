package com.ktds.FitnessPartner.board.controller;

import com.ktds.FitnessPartner.board.dto.CommentDto;
import com.ktds.FitnessPartner.board.entity.Board;
import com.ktds.FitnessPartner.board.entity.Comment;
import com.ktds.FitnessPartner.board.service.BoardService;
import com.ktds.FitnessPartner.board.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    private final BoardService boardService;
    private final CommentService commentService;

    @GetMapping("/detail")
    public String detail(@RequestParam("id")Long id, Model model) {
        Board board = boardService.findById(id);
        List<Comment> commentList = commentService.findAllByBoardId(CommentDto.builder().boardId(id).build());
        model.addAttribute("pageNum", 3);
        model.addAttribute("board", board);
        model.addAttribute("comments", commentList);
        return "food_share_detail";
    }
    @PostMapping("/create")
    public @ResponseBody Comment commentCreate(@ModelAttribute CommentDto commentDTO) {
        System.out.println(commentDTO.getBoardId());
        System.out.println(commentDTO.getComments());
        return commentService.save(commentDTO);
//        return "redirect:/comment/detail?id="+commentDTO.getBoardId();
    }
}
