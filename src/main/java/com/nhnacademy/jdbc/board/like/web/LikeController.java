package com.nhnacademy.jdbc.board.like.web;

import com.nhnacademy.jdbc.board.like.service.LikeService;
import com.nhnacademy.jdbc.board.user.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@Controller
public class LikeController {
    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @GetMapping("/likeChange/{postNum}")
    public String getLikeChange(@PathVariable long postNum,
                                HttpSession session) {
        User user = (User) session.getAttribute("login");
        likeService.changeLike(user.getUserNum(), postNum);
        return "redirect:/postDetail/"+postNum;
    }
}
