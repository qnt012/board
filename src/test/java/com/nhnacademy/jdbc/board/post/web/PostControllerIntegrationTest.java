package com.nhnacademy.jdbc.board.post.web;


import static org.assertj.core.api.Assertions.assertThat;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.nhnacademy.jdbc.board.config.RootConfig;
import com.nhnacademy.jdbc.board.file.service.FileService;
import com.nhnacademy.jdbc.board.like.service.LikeService;
import com.nhnacademy.jdbc.board.post.domain.Post;
import com.nhnacademy.jdbc.board.post.domain.PostView;
import com.nhnacademy.jdbc.board.post.service.CommentService;
import com.nhnacademy.jdbc.board.post.service.PostService;
import com.nhnacademy.jdbc.board.user.domain.User;
import com.nhnacademy.jdbc.board.user.service.UserService;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.codec.binary.Base64;
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
public class PostControllerIntegrationTest {
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private LikeService likeService;
    @Autowired
    private FileService fileService;
    private MockHttpSession mockHttpSession;
    private User user;
    private Post post;
    private File file;
    private MockMvc mockMvc;
    private String id;
    private String pwd;
    private String fileString;

    @BeforeEach
    void setUp() throws IOException {
        mockMvc = MockMvcBuilders.standaloneSetup(new PostController(postService, commentService, likeService,fileService))
            .build();
        mockHttpSession = new MockHttpSession();
        id = "user";
        pwd = "1234";
        Optional<User> user = userService.login(id, pwd);
        mockHttpSession.setAttribute("login", user.get().getUserAuthInfo());
        file = new File("E:\\file.txt");
        file.createNewFile();
        fileString = fileToString(file);


    }

    @Test
    @DisplayName("GET 요청을 통한 PostView 테스팅")
    void getPostViewTest() throws Exception {
        int page = 1;
        List<PostView> postViews = postService.viewPosts(page);
        user = (User) mockHttpSession.getAttribute("login");
        MvcResult mvcResult = mockMvc.perform(get("/postView/{page}",page).session(mockHttpSession))
            .andExpect(status().isOk())
            .andExpect(model().attribute("postViews",postViews))
            .andExpect(model().attribute("page",page))
            .andExpect(model().attribute("maxPage", postViews.size() / 20 + page))
            .andExpect(model().attribute("isAdmin",user.isAdmin()))
            .andDo(print())
            .andExpect(view().name("postView"))
            .andReturn();

        assertThat(mvcResult.getRequest().getSession()).isNotNull();
    }

    @Test
    @DisplayName("GET 요청을 통한 Post Insert 주소 Test")
    void getPostInsert() throws Exception {
        mockMvc.perform(get("/postInsert"))
            .andExpect(status().isOk())
            .andExpect(view().name("postInsertForm"));
    }

    @Test
    @DisplayName("Post 요청을 통한 Post Insert 주소 Redirection Test")
    void postPostInsert() throws Exception {
        mockMvc.perform(post("/postInsert").session(mockHttpSession)
                .param("title","Hello Titles")
                .param("content","any content"))
            .andExpect(status().is3xxRedirection())
            .andExpect(view().name("redirect:/postView/0"));
    }

    @Test
    @DisplayName("Post 요청을 통해 파일 전송 확인 테스트")
    void postPostIncludeFileInsert() throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("/postInsert").session(mockHttpSession)
                .param("title","Hello Titles")
                .param("content","any content")
                .param("file",fileString))
            .andExpect(status().is3xxRedirection())
            .andExpect(view().name("redirect:/postView/0"))
            .andReturn();
    }

    @Test
    @DisplayName("GET 요청을 통한 게시글 확인")
    void getPostDetailTest() throws Exception {
        user = (User) mockHttpSession.getAttribute("login");
        post = postService.getPost(1);
        mockMvc.perform(get("/postDetail/{postNum}",1).session(mockHttpSession))
            .andExpect(model().attribute("post",post))
            .andExpect(model().attribute("isWriter", Objects.equals(user.getUserId(),post.getWriterId())))
            .andExpect(model().attribute("isAdmin", user.isAdmin()))
            .andExpect(model().attribute("isLikePost",likeService.isLikePost(user.getUserNum(), 1)))
            .andExpect(model().attribute("fileName",fileService.getFileName(post.getPostNum())))
            .andExpect(status().isOk())
            .andExpect(view().name("postDetailView"));
    }


    @Test
    @DisplayName("GET 요청을 통한 게시글 수정 테스트")
    void getPostModifyTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/postModify/{postNum}",2).session(mockHttpSession))
            .andExpect(model().attribute("post",postService.getPost(2)))
            .andExpect(status().isOk())
            .andExpect(view().name("postModifyForm"))
            .andReturn();

        assertThat(mvcResult.getRequest().getAttribute("post")).isEqualTo(postService.getPost(2));

    }

    @Test
    @DisplayName("Post 요청을 통한 PostModify 게시글 수정 요청")
    void postPostModify() throws Exception {
        mockMvc.perform(post("/postModify/{postNum}",1).session(mockHttpSession)
                .param("title","Hello Titles")
                .param("content","any content"))
            .andExpect(status().is3xxRedirection())
            .andExpect(view().name("redirect:/postView/0"));
    }

    @Test
    @DisplayName("GET 요청을 통한 게시글 삭제 테스트")
    void getPostDelete() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/postDelete/{postNum}",3)
                .param("postNum","3"))
            .andExpect(status().is3xxRedirection())
            .andExpect(view().name("redirect:/postView/0"))
            .andReturn();
    }


    @Test
    @DisplayName("GET 요청을 통한 postDeleteList 테스트")
    void getPostDeleteList() throws Exception {
        int page = 1;
        List<PostView> postViews = postService.viewDeletePosts(page);
        MvcResult mvcResult = mockMvc.perform(get("/postDeleteList/{page}",1))
            .andExpect(model().attribute("postViews",postViews))
            .andExpect(model().attribute("page",page))
            .andExpect(model().attribute("maxPage",postViews.size() / 20 + page))
            .andExpect(status().isOk())
            .andExpect(view().name("postDeleteList"))
            .andReturn();
    }


    @Test
    @DisplayName("GET 요청을 통한 게시물 복원 테스트")
    void getPostRestore() throws Exception {
        mockMvc.perform(get("/postRestore/{postNum}",1))
            .andExpect(status().is3xxRedirection())
            .andExpect(view().name("redirect:/postDeleteList/0"))
            .andReturn();
    }


    @Test
    @DisplayName("GET 요청을 통한 게시물 좋아요 리스트 테스트")
    void getPostLikeList() throws Exception {
        user = (User) mockHttpSession.getAttribute("login");
        int page = 1;
        List<PostView> postViews = postService.viewLikePosts(user.getUserNum(), page);
        mockMvc.perform(get("/postLikeList/{page}",1).session(mockHttpSession))
            .andExpect(model().attribute("postViews",postViews))
            .andExpect(model().attribute("page",page))
            .andExpect(model().attribute("maxPage", postViews.size() / 20 + page))
            .andExpect(status().isOk())
            .andExpect(view().name("postLikeList"));
    }

    @Test
    @DisplayName("POST 요청을 통한 게시물 검색 테스트")
    void postSearch() throws Exception {
        user = (User) mockHttpSession.getAttribute("login");
        int page = 1;
        List<PostView> postViews = postService.findPostsByTitle("Hello Titles",1);
        mockMvc.perform(post("/search/{page}",page)
                .param("title", "Hello Titles"))
            .andExpect(model().attribute("postViews",postViews))
            .andExpect(model().attribute("page",page))
            .andExpect(model().attribute("maxPage", postViews.size() / 20 + page))
            .andExpect(status().isOk())
            .andExpect(view().name("postSearchView"));
    }
    public String fileToString(File file) throws IOException {
        String fileString = new String();
        FileInputStream inputStream =  null;
        ByteArrayOutputStream byteOutStream = null;
        try {
            inputStream = new FileInputStream(file);
            byteOutStream = new ByteArrayOutputStream();
            int len = 0;
            byte[] buf = new byte[1024];
            while ((len = inputStream.read(buf)) != -1) {
                byteOutStream.write(buf, 0, len);
            }
            byte[] fileArray = byteOutStream.toByteArray();
            fileString = new String(Base64.encodeBase64(fileArray));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            inputStream.close();
            byteOutStream.close();
        }
        return fileString;
    }

}