package com.nhnacademy.jdbc.board.post.service;

import com.nhnacademy.jdbc.board.post.domain.Post;
import com.nhnacademy.jdbc.board.post.domain.PostView;

import java.util.List;

public interface PostService {
    Post getPost(long postNum);
    List<PostView> viewPosts(int page);
    List<PostView> viewDeletePosts(int page);
    List<PostView> viewLikePosts(long userNum, int page);
    List<PostView> findPostsByTitle(String keyword, int page);
    void createPost(long writerNum, String title, String content);
    void modifyPost(long postNum, String title, String content, long modifierNum);
    void deletePost(long postNum);
    void restorePost(long postNum);
    long getRecentPostNum();
}
