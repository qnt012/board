package com.nhnacademy.jdbc.board.post.web;

import com.nhnacademy.jdbc.board.post.service.PostService;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }


    @GetMapping("/postView")
    public String getPostView(ModelMap modelMap){
        modelMap.put("postViews", postService.viewPosts());
        return "postView";
    }

    @GetMapping("/postInsert")
    public String getPostInsert() {
        return "postInsertForm";
    }

    @PostMapping("/postInsert")
    public String postPostInsert(@RequestParam String title,
                                 @RequestParam String content,
                                 HttpSession session) {

        postService.createPost((Integer) session.getAttribute("login"), title, content);
        return "redirect:/postView";
    }
}
