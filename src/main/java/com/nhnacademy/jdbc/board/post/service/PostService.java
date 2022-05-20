package com.nhnacademy.jdbc.board.post.service;

import com.nhnacademy.jdbc.board.post.domain.Comment;
import com.nhnacademy.jdbc.board.post.domain.Post;
import com.nhnacademy.jdbc.board.post.domain.PostView;

import java.util.List;

public interface PostService {
    Post getPost(long postNum);
    List<PostView> viewPosts(int page);
    void createPost(long writerNum, String title, String content);
    void modifyPost(long postNum, String title, String content, long modifierNum);
    int getMaxPage();
    List<Comment> viewComments(long postNum);
    void deletePost(long postNum);
    void createComment(long postNum, long writerNum, String commentContent);
    void restorePost(long postNum);
    Comment getComment(long commentNum);
    long modifyComment(long commentNum, String commentContent);
}
