package com.nhnacademy.jdbc.board.post.service;

import com.nhnacademy.jdbc.board.post.domain.PostView;

import java.util.List;

public interface PostService {
    List<PostView> viewPosts();
}
