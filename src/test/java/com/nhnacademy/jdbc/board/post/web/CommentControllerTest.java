package com.nhnacademy.jdbc.board.post.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.nhnacademy.jdbc.board.post.domain.Comment;
import com.nhnacademy.jdbc.board.post.service.CommentService;
import com.nhnacademy.jdbc.board.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class CommentControllerTest {
    private MockMvc mockMvc;
    private CommentService commentService;
    private User user;
    private MockHttpSession session;

    @BeforeEach
    void setUp(){
        user = new User(1,"admin","1234",false);
        commentService = mock(CommentService.class);
        session = new MockHttpSession();
        mockMvc = MockMvcBuilders.standaloneSetup(new CommentController(commentService))
            .build();
        user = new User(1,"admin","1234",false);
        session.setAttribute("login",user);
    }

    @Test
    void postCommentInsertTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("/commentInsert/1")
                .session(session)
                .param("commentContent","hi"))
            .andExpect(status().is3xxRedirection())
            .andExpect(view().name("redirect:/postDetail/{postNum}"))
            .andReturn();

        assertThat(session.getAttribute("login")).isEqualTo(user);
    }

    @Test
    void getCommentModifyTest() throws Exception{
        Comment comment = new Comment(1,"bomin","comment");
        when(commentService.getComment(1)).thenReturn(comment);
        MvcResult mvcResult = mockMvc.perform(get("/commentModify/{commentNum}",1))
            .andExpect(status().isOk())
            .andExpect(model().attribute("comment", commentService.getComment(1)))
            .andExpect(view().name("commentModifyForm"))
            .andReturn();
        assertThat(mvcResult.getModelAndView().getModelMap().getAttribute("comment")).isEqualTo(comment);
    }

    @Test
    void postCommentModifyTest() throws  Exception{
        when(commentService.modifyComment(anyLong(), anyString())).thenReturn(1L);
        MvcResult mvcResult = mockMvc.perform(post("/commentModify/{commentNum}",1)
                .param("commentContent","ttttt"))
            .andExpect(status().is3xxRedirection())
            .andExpect(view().name("redirect:/postDetail/1"))
            .andReturn();
    }

    @Test
    void getCommentDeleteTest() throws Exception{
        when(commentService.removeComment(anyLong())).thenReturn(1L);
        MvcResult mvcResult = mockMvc.perform(get("/commentDelete/{commentNum}",1))
            .andExpect(status().is3xxRedirection())
            .andExpect(view().name("redirect:/postDetail/1"))
            .andReturn();
    }
}
