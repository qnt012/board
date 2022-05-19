package com.nhnacademy.jdbc.board.post.web;

import com.nhnacademy.jdbc.board.post.service.PostService;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }


    @GetMapping("/postView/{page}")
    public String getPostView(@PathVariable int page, ModelMap modelMap){
        modelMap.put("postViews", postService.viewPosts(page));
        modelMap.put("page", page);
        modelMap.put("maxPage", postService.getMaxPage());
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
