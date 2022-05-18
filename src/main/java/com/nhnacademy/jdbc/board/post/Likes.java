package com.nhnacademy.jdbc.board.post;

public class Likes {
    private final long user_num;
    private final long post_num;

    public Likes(long user_num, long post_num) {
        this.user_num = user_num;
        this.post_num = post_num;
    }
}
