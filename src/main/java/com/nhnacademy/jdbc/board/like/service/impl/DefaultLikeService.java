package com.nhnacademy.jdbc.board.like.service.impl;

import com.nhnacademy.jdbc.board.like.mapper.LikeMapper;
import com.nhnacademy.jdbc.board.like.service.LikeService;
import org.springframework.stereotype.Service;

@Service
public class DefaultLikeService implements LikeService {
    private final LikeMapper likeMapper;

    public DefaultLikeService(LikeMapper likeMapper) {
        this.likeMapper = likeMapper;
    }


    @Override
    public boolean isLikePost(long userNum, long postNum) {
        return likeMapper.selectLike(userNum, postNum).isPresent();
    }
}
