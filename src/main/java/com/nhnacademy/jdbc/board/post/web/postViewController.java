package com.nhnacademy.jdbc.board.post.web;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Service
public class postViewController {

    @GetMapping("/postView")
    public String getPostView(){
        return "postView";
    }
}
