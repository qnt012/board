package com.nhnacademy.jdbc.board.post.service.impl;

import com.nhnacademy.jdbc.board.post.domain.Post;
import com.nhnacademy.jdbc.board.post.domain.PostView;
import com.nhnacademy.jdbc.board.post.mapper.PostMapper;
import com.nhnacademy.jdbc.board.post.service.PostService;
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
    public List<PostView> viewDeletePosts(int page) {
        return postMapper.selectInvisiblePostViews(20 * page);
    }

    @Override
    public List<PostView> viewLikePosts(long userNum, int page) {
        return postMapper.selectLikePostViews(userNum, page);
    }

    @Override
    public List<PostView> findPostsByTitle(String keyword, int page) {
        return postMapper.selectPostViewsByKeyword(keyword, page);
    }

    @Override
    public void createPost(long writerNum, String title, String content) {
        postMapper.insertPost(writerNum, title, content);
    }

    @Override
    public void modifyPost(long postNum, String title, String content, long modifierNum) {
        postMapper.updatePost(postNum, title,content, modifierNum);
    }

    @Override
    public void deletePost(long postNum) {
        postMapper.updatePostVisibility(postNum, false);
    }

    @Override
    public void restorePost(long postNum) {
        postMapper.updatePostVisibility(postNum, true);
    }

    @Override
    public long getRecentPostNum() {
        return postMapper.selectLastInsertId();
    }

}
