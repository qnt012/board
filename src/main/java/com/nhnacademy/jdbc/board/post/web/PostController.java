package com.nhnacademy.jdbc.board.post.web;

import com.nhnacademy.jdbc.board.file.service.FileService;
import com.nhnacademy.jdbc.board.like.service.LikeService;
import com.nhnacademy.jdbc.board.post.domain.Post;
import com.nhnacademy.jdbc.board.post.domain.PostView;
import com.nhnacademy.jdbc.board.post.service.CommentService;
import com.nhnacademy.jdbc.board.post.service.PostService;
import com.nhnacademy.jdbc.board.user.domain.User;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class PostController {
    private final PostService postService;
    private final CommentService commentService;
    private final LikeService likeService;
    private final FileService fileService;

    public PostController(PostService postService,
                          CommentService commentService,
                          LikeService likeService, FileService fileService) {
        this.postService = postService;
        this.commentService = commentService;
        this.likeService = likeService;
        this.fileService = fileService;
    }


    @GetMapping("/postView/{page}")
    public String getPostView(@PathVariable int page, ModelMap modelMap, HttpSession session){
        User user = (User) session.getAttribute("login");
        List<PostView> postViews = postService.viewPosts(page);
        modelMap.put("postViews", postViews);
        modelMap.put("page", page);
        modelMap.put("maxPage", postViews.size() / 20);
        modelMap.put("isAdmin", user.isAdmin());
        return "postView";
    }

    @GetMapping("/postInsert")
    public String getPostInsert() {
        return "postInsertForm";
    }

    @PostMapping("/postInsert")
    public String postPostInsert(@RequestParam String title,
                                 @RequestParam String content,
                                 @RequestParam(required = false) MultipartFile file,
                                 HttpSession session) throws IOException {
        User user = (User) session.getAttribute("login");
        postService.createPost(user.getUserNum(), title, content);
        if (!file.isEmpty()) {
            ServletContext context = session.getServletContext();
            String path = context.getRealPath("");
            String filename = file.getOriginalFilename();

            byte[] bytes = file.getBytes();
            try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(path + File.separator + filename))) {
                stream.write(bytes);
                stream.flush();
            }

            fileService.uploadFile(postService.getRecentPostNum(), filename);
        }
        return "redirect:/postView/0";
    }

    @GetMapping("/postDetail/{postNum}")
    public String getPostDetail(@PathVariable long postNum, ModelMap modelMap, HttpSession session) {
        User user = (User) session.getAttribute("login");
        Post post = postService.getPost(postNum);
        modelMap.put("post", post);
        modelMap.put("comments", commentService.viewComments(postNum));
        modelMap.put("isWriter", Objects.equals(user.getUserId(), post.getWriterId()));
        modelMap.put("isAdmin", user.isAdmin());
        modelMap.put("isLikePost", likeService.isLikePost(user.getUserNum(), postNum));
        return "postDetailView";
    }

    @GetMapping("/postModify/{postNum}")
    public String getPostModify(@PathVariable long postNum, ModelMap modelMap) {
        modelMap.put("post", postService.getPost(postNum));
        return "postModifyForm";
    }

    @PostMapping("/postModify/{postNum}")
    public String postPostModify(@PathVariable long postNum,
                                 @RequestParam String title,
                                 @RequestParam String content,
                                 HttpSession session) {
        User user = (User) session.getAttribute("login");
        postService.modifyPost(postNum, title, content, user.getUserNum());
        return "redirect:/postView/0";
    }

    @GetMapping("/postDelete/{postNum}")
    public String getPostDelete(@PathVariable long postNum) {
        postService.deletePost(postNum);
        return "redirect:/postView/0";
    }

    @GetMapping("/postDeleteList/{page}")
    public String getPostDeleteList(@PathVariable int page, ModelMap modelMap){
        List<PostView> postViews = postService.viewDeletePosts(page);
        modelMap.put("postViews", postViews);
        modelMap.put("page", page);
        modelMap.put("maxPage", postViews.size() / 20);
        return "postDeleteList";
    }


    @GetMapping("/postRestore/{postNum}")
    public String getPostRestore(@PathVariable long postNum) {
        postService.restorePost(postNum);
        return "redirect:/postDeleteList/0";
    }

    @GetMapping("/postLikeList/{page}")
    public String getPostLikeList(@PathVariable int page, ModelMap modelMap, HttpSession session){
        User user = (User) session.getAttribute("login");
        List<PostView> postViews = postService.viewLikePosts(user.getUserNum(), page);
        modelMap.put("postViews", postViews);
        modelMap.put("page", page);
        modelMap.put("maxPage", postViews.size() / 20);
        return "postLikeList";
    }

    @PostMapping("/search/{page}")
    public String postSearch(@PathVariable int page,
                                 @RequestParam String title,
                                 ModelMap modelMap) {
        List<PostView> postViews = postService.findPostsByTitle(title, page);
        modelMap.put("postViews", postViews);
        modelMap.put("page", page);
        modelMap.put("maxPage", postViews.size() / 20);
        return "postSearchView";
    }
}