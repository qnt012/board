package com.nhnacademy.jdbc.board.post;

public class Likes {
    private final long userNum;
    private final long postNum;

    public Likes(long userNum, long postNum) {
        this.userNum = userNum;
        this.postNum = postNum;
    }
}
