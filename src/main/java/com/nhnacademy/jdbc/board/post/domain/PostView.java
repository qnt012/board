package com.nhnacademy.jdbc.board.post.domain;

import lombok.Getter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
public class PostView {
    private final long postNum;
    private final String title;
    private final String writerId;
    private final Timestamp writeTime;
    private final int commentCount;

    public PostView(long postNum, String title, String writerId, Timestamp writeTime, int commentCount) {
        this.postNum = postNum;
        this.title = title;
        this.writerId = writerId;
        this.writeTime = writeTime;
        this.commentCount = commentCount;
    }
}
