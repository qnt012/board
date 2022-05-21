package com.nhnacademy.jdbc.board.post.web;

import com.nhnacademy.jdbc.board.post.service.CommentService;
import com.nhnacademy.jdbc.board.user.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/commentInsert/{postNum}")
    public String postPostModify(@PathVariable long postNum,
                                 @RequestParam String commentContent,
                                 HttpSession session) {
        User user = (User) session.getAttribute("login");
        commentService.createComment(postNum, user.getUserNum(), commentContent);
        return "redirect:/postDetail/{postNum}";
    }


    @GetMapping("/commentModify/{commentNum}")
    public String getCommentModify(@PathVariable long commentNum, ModelMap modelMap) {
        modelMap.put("comment", commentService.getComment(commentNum));
        return "commentModifyForm";
    }

    @PostMapping("/commentModify/{commentNum}")
    public String postCommentModify(@PathVariable long commentNum,
                                    @RequestParam String commentContent) {
        long postNum = commentService.modifyComment(commentNum, commentContent);
        return "redirect:/postDetail/"+postNum;
    }

    @GetMapping("/commentDelete/{commentNum}")
    public String getCommentDelete(@PathVariable long commentNum) {
        long postNum = commentService.removeComment(commentNum);
        return "redirect:/postDetail/"+postNum;
    }
}
