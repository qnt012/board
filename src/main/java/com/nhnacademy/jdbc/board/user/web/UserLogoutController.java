package com.nhnacademy.jdbc.board.user.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class UserLogoutController {

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("login");
        session.invalidate();
        return "redirect:/";
    }
}
