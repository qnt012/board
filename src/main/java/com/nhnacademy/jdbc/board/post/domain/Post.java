package com.nhnacademy.jdbc.board.post.domain;

import java.sql.Timestamp;
import lombok.Getter;

@Getter
public class Post {
    private final long postNum;
    private final String title;
    private final String content;
    private final String writerId;
    private final String modifierId;
    private final Timestamp writeTime;
    private final Timestamp modifyTime;

    public Post(long postNum, String title, String content, String writerId,
                String modifierId, Timestamp writeTime, Timestamp modifyTime) {
        this.postNum = postNum;
        this.title = title;
        this.content = content;
        this.writerId = writerId;
        this.modifierId = modifierId;
        this.writeTime = writeTime;
        this.modifyTime = modifyTime;
    }
}
