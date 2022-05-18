package com.nhnacademy.jdbc.board.index.web;

import com.nhnacademy.jdbc.board.user.domain.User;
import com.nhnacademy.jdbc.board.user.service.UserService;
import com.nhnacademy.jdbc.board.user.service.impl.DefaultUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping
@Slf4j
public class IndexController {
    private final UserService userService;

    public IndexController(DefaultUserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = {"/","/index.nhn"})
    public String index(){
        Optional<User> user = userService.getUser(1l);
        if(user.isPresent()){
            log.debug("User : {}",user.get());
        }
        return "index/index";
    }
}
