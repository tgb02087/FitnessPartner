package com.ktds.FitnessPartner.board.controller;

import com.ktds.FitnessPartner.board.dto.BoardDto;
import com.ktds.FitnessPartner.board.dto.PagingDto;
import com.ktds.FitnessPartner.board.entity.Board;
import com.ktds.FitnessPartner.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/food_share")
    public String food_share(@RequestParam(defaultValue = "1", value = "page") int page, Model model) {
        List<Board> boards = boardService.findAll();
        List<Board> newList = new ArrayList<>();
        int maxSize = boards.size()%3==0?boards.size()/3:boards.size()/3+1;
        int start = 0;
        int end = 0;
        if(page%3!=0) {
            start = ((page/3+1)*3)-2;
            end = start +2;
        }
        else {
            start = page-2;
            end = start +2;
        }
        int cnt = 0;
        for(int i=page*3-3; i<boards.size(); i++) {
            if(cnt==3) break;
            newList.add(boards.get(i));
            cnt++;
        }
        if(maxSize<end) {
            end = maxSize;
        }
        model.addAttribute("boards", newList);
        model.addAttribute("pageNum", 3);
        PagingDto pagingDTO = new PagingDto(page, start, end);
        model.addAttribute("paging", pagingDTO);
        return "food_share2";
    }

    @GetMapping("/create")
    public String createPage(Model model) {
        model.addAttribute("pageNum", 3);
        return "food_share_create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute BoardDto boardDTO, MultipartRequest multipartRequest) throws IOException {
        MultipartFile file = multipartRequest.getFile("file");
        String url = boardService.upload(file);
        boardDTO.setImgage(url);
        boardService.save(boardDTO);
        return "redirect:/board/food_share";
    }
}
