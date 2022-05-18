package com.nhnacademy.jdbc.board.post.web;

import com.nhnacademy.jdbc.board.post.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PostViewController {
    private final PostService postService;

    public PostViewController(PostService postService) {
        this.postService = postService;
    }


    @GetMapping("/postView")
    public String getPostView(ModelMap modelMap){
        modelMap.put("postViews", postService.viewPosts());
        return "postView";
    }
}
