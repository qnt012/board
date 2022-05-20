package com.nhnacademy.jdbc.board.like.service;

public interface LikeService {
    boolean isLikePost(long userNum, long postNum);
}
