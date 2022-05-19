package com.nhnacademy.jdbc.board.post.service.impl;

import com.nhnacademy.jdbc.board.post.domain.Post;
import com.nhnacademy.jdbc.board.post.domain.PostView;
import com.nhnacademy.jdbc.board.post.mapper.PostMapper;
import com.nhnacademy.jdbc.board.post.service.PostService;
import java.util.Optional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultPostService implements PostService {
    private final PostMapper postMapper;

    public DefaultPostService(PostMapper postMapper) {
        this.postMapper = postMapper;
    }

    @Override
    public Post getPost(long postNum) {
        return postMapper.selectPost(postNum);
    }

    @Override
    public List<PostView> viewPosts(int page) {
        return postMapper.selectPostViews(20 * page);
    }

    @Override
    public void createPost(long writerNum, String title, String content) {
        postMapper.insertPost(writerNum, title, content);
    }

    @Override
    public int getMaxPage() {
        return postMapper.selectPostCount() / 20;
    }
}
