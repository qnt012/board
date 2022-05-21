package com.nhnacademy.jdbc.board.post.web;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.nhnacademy.jdbc.board.config.RootConfig;
import com.nhnacademy.jdbc.board.like.service.LikeService;
import com.nhnacademy.jdbc.board.post.domain.Post;
import com.nhnacademy.jdbc.board.post.domain.PostView;
import com.nhnacademy.jdbc.board.post.service.CommentService;
import com.nhnacademy.jdbc.board.post.service.PostService;
import com.nhnacademy.jdbc.board.user.domain.User;
import com.nhnacademy.jdbc.board.user.service.UserService;
import java.util.List;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringJUnitConfig(RootConfig.class)
@WebAppConfiguration
@Rollback
public class PostControllerIntergrationTest {
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private LikeService likeService;
    private MockHttpSession mockHttpSession;
    private User user;
    private Post post;
    private MockMvc mockMvc;
    private String id;
    private String pwd;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new PostController(postService, commentService, likeService))
            .build();
        mockHttpSession = new MockHttpSession();
        id = "user";
        pwd = "1234";
        Optional<User> user = userService.login(id, pwd);
        mockHttpSession.setAttribute("login", user.get().getUserAuthInfo());
    }

    @Test
    @DisplayName("GET 요청을 통한 PostView 테스팅")
    void getPostViewTest() throws Exception {
        int page = 1;
        List<PostView> postViews = postService.viewPosts(page);
        user = (User) mockHttpSession.getAttribute("login");
        mockMvc.perform(get("/postView/{page}",page).session(mockHttpSession))
            .andExpect(status().isOk())
            .andExpect(model().attribute("postViews",postViews))
            .andExpect(model().attribute("page",page))
            .andExpect(model().attribute("maxPage", postViews.size() / 20))
            .andExpect(model().attribute("isAdmin",user.isAdmin()))
            .andDo(print())
            .andExpect(view().name("postView"));
    }
}
