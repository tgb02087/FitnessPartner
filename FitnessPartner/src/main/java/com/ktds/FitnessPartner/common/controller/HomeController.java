package com.ktds.FitnessPartner.common.controller;

import com.ktds.FitnessPartner.board.controller.BoardController;
import com.ktds.FitnessPartner.board.entity.Board;
import com.ktds.FitnessPartner.board.service.BoardService;
import com.ktds.FitnessPartner.common.config.JwtProvider;
import com.ktds.FitnessPartner.user.service.BodyInfoService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final BoardService boardService;
    private final BodyInfoService bodyInfoService;
    private final JwtProvider jwtProvider;
    @GetMapping("/")
    public String index(HttpServletRequest request, Model model) {
        String token = jwtProvider.getToken(request);
        if(token.equals("")) return "index";
        else {
            model.addAttribute("pageNum", 1);
            return "home";
        }
    }
    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("pageNum", 1);
        return "home";
    }
    @GetMapping("/food_plan")
    public String food_plan(HttpServletRequest request, Model model) {
        String id = request.getAttribute("id").toString();
        int kcal = bodyInfoService.getKcal(id);
        model.addAttribute("kcal", kcal);
        model.addAttribute("pageNum", 2);
        return "food_plan";
    }
    @GetMapping("/exercise_plan")
    public String exercise_plan(Model model) {
        model.addAttribute("pageNum", 4);
        return "exercise_plan";
    }
    @GetMapping("/mypage")
    public String mypage(Model model) {
        model.addAttribute("pageNum", 5);
        return "mypage";
    }
    @GetMapping("/test")
    public String test(Model model) {
        List<Board> boards = boardService.findAll();
        model.addAttribute("boards", boards);
        return "test";
    }
    @GetMapping("/calender")
    public String cal() {
        return "calender";
    }
}
