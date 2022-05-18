package com.nhnacademy.jdbc.board.user.web;

import com.nhnacademy.jdbc.board.user.domain.User;
import com.nhnacademy.jdbc.board.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class UserLoginController {
    private final UserService userService;

    public UserLoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public String postLogin(@RequestParam String id,
                            @RequestParam String pwd,
                            HttpSession session) {
        Optional<User> user = userService.login(id, pwd);
        if (user.isPresent()) {
            session.setAttribute("login", true);
            return "redirect:/postView";
        }
        return "redirect:/";
    }
}
