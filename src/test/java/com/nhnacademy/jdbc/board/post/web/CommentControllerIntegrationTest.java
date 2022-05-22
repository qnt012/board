package com.nhnacademy.jdbc.board.post.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.nhnacademy.jdbc.board.config.RootConfig;
import com.nhnacademy.jdbc.board.post.service.CommentService;
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
public class CommentControllerIntegrationTest {
    private MockMvc mockMvc;
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;
    private User user;
    private MockHttpSession session;
    private String id;
    private String pwd;

    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(new CommentController(commentService))
            .build();
        session = new MockHttpSession();
        id = "user";
        pwd = "1234";
        Optional<User> user = userService.login(id, pwd);
        session.setAttribute("login", user.get().getUserAuthInfo());
    }

    @Test
    @DisplayName("POST 요청으로 댓글 입력 테스팅")
    void postCommentInsertTest() throws Exception {
        int postNum = 1;
        user = (User) session.getAttribute("login");
        MvcResult mvcResult = mockMvc.perform(post("/commentInsert/{postNum}",postNum).session(session)
                .param("commentContent","good post"))
            .andExpect(status().is3xxRedirection())
            .andExpect(view().name("redirect:/postDetail/{postNum}"))
            .andReturn();
        assertThat(mvcResult.getModelAndView().getViewName()).isEqualTo("redirect:/postDetail/{postNum}");
    }

    @Test
    @DisplayName("GET 요청으로 댓글 수정 확인")
    void getCommentModifyTest() throws Exception {
        mockMvc.perform(get("/commentModify/{commentNum}",1))
            .andExpect(model().attribute("comment",commentService.getComment(1)))
            .andExpect(status().isOk())
            .andExpect(view().name("commentModifyForm"));
    }


//    @Test
//    @DisplayName("POST 요청으로 댓글 수정 테스팅")
//    void postCommentModifyTest() throws Exception {
//        long commentNum = 1;
//        int postNum = 1;
//        user = (User) session.getAttribute("login");
//        MvcResult mvcResult = mockMvc.perform(post("/commentModify/{commentNum}",commentNum)
//                .param("commentContent","modify comment"))
//            .andExpect(status().is3xxRedirection())
//            .andExpect(view().name("redirect:/postDetail/"+postNum))
//            .andReturn();
//        assertThat(mvcResult.getModelAndView().getViewName()).isEqualTo("redirect:/postDetail/"+postNum);
//    }



//    @Test
//    @DisplayName("GET 요청으로 댓글 삭제 확인")
//    void getCommentDelete() throws Exception {
//        int postNum = 1;
//        mockMvc.perform(get("/commentDelete/{commentNum}",1))
//            .andExpect(model().attribute("comment",commentService.removeComment(1)))
//            .andExpect(status().isOk())
//            .andExpect(view().name("redirect:/postDetail/"+postNum));
//    }
}
