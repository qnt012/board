package com.nhnacademy.jdbc.board.post.domain;

public class Comment {
    private final long comment_num;
    private final long post_num;
    private final long writer_num;
    private final String comment_content;

    public Comment(long comment_num, long post_num, long writer_num, String comment_content) {
        this.comment_num = comment_num;
        this.post_num = post_num;
        this.writer_num = writer_num;
        this.comment_content = comment_content;
    }
}
