package com.nhnacademy.jdbc.board.like.web;


import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.nhnacademy.jdbc.board.config.RootConfig;
import com.nhnacademy.jdbc.board.like.service.LikeService;
import com.nhnacademy.jdbc.board.post.web.CommentController;
import com.nhnacademy.jdbc.board.user.domain.User;
import com.nhnacademy.jdbc.board.user.service.UserService;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringJUnitConfig(RootConfig.class)
@WebAppConfiguration
@Rollback
public class LikeControllerIntegrationTest {
    private MockMvc mockMvc;
    @Autowired
    private LikeService likeService;
    @Autowired
    private UserService userService;
    private User user;
    private MockHttpSession session;

    private String userId;
    private String userPwd;


    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(new LikeController(likeService))
            .build();
        session = new MockHttpSession();
        userId = "user";
        userPwd = "1234";
        Optional<User> user = userService.login(userId, userPwd);
        session.setAttribute("login", user.get().getUserAuthInfo());
    }


    @Test
    @DisplayName("likeService 상태 확인")
    void userServiceNonNullTest(){
        assertThat(likeService).isNotNull();
    }


    @Test
    @DisplayName("getLikeChange 좋아요 => 좋아요 취소 작업 테스팅")
    void getLikeChange() throws Exception {
        int postNum = 1;
        user = (User) session.getAttribute("login");
        MvcResult mvcResult = mockMvc.perform(get("/likeChange/{postNum}",1).session(session))
            .andExpect(status().is3xxRedirection())
            .andExpect(view().name("redirect:/postDetail/"+postNum))
            .andReturn();
        assertThat(mvcResult.getModelAndView().getViewName()).isEqualTo("redirect:/postDetail/"+postNum);
    }

}
