package com.nhnacademy.jdbc.board.post.domain;

import lombok.Getter;

@Getter
public class Comment {
    private final long commentNum;
    private final String writerId;
    private final String commentContent;

    public Comment(long commentNum, String writerId, String commentContent) {
        this.commentNum = commentNum;
        this.writerId = writerId;
        this.commentContent = commentContent;
    }
}
