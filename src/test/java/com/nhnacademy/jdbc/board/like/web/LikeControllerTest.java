package com.nhnacademy.jdbc.board.like.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.nhnacademy.jdbc.board.like.service.LikeService;
import com.nhnacademy.jdbc.board.post.domain.Comment;
import com.nhnacademy.jdbc.board.post.web.PostController;
import com.nhnacademy.jdbc.board.user.domain.User;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class LikeControllerTest {
    private MockMvc mockMvc;
    private LikeService likeService;
    private User user;
    private MockHttpSession session;

    @BeforeEach
    void setUp(){
        likeService = mock(LikeService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new LikeController(likeService))
            .build();
        user = new User(1,"admin","1234",false);
        session = new MockHttpSession();
        session.setAttribute("login", user);
    }
    @AfterEach
    void tearDown() {
        session.clearAttributes();
    }

    @Test
    void getLikeChangeTest() throws Exception {
        doNothing().when(likeService).changeLike(user.getUserNum(), 1);
        mockMvc.perform(get("/likeChange/{postNum}",1).session(session))
            .andExpect(status().is3xxRedirection())
            .andExpect(view().name("redirect:/postDetail/1"))
            .andReturn();
        verify(likeService, atLeastOnce()).changeLike(anyLong(),anyLong());
    }

}
