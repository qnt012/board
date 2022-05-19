package com.nhnacademy.jdbc.board.post.service;

import com.nhnacademy.jdbc.board.post.domain.Comment;
import com.nhnacademy.jdbc.board.post.domain.Post;
import com.nhnacademy.jdbc.board.post.domain.PostView;

import java.util.List;

public interface PostService {
    Post getPost(long postNum);
    List<PostView> viewPosts(int page);
    void createPost(long writerNum, String title, String content);
    int getMaxPage();
    List<Comment> viewComments(long postNum);
}
