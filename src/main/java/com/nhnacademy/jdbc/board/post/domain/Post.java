package com.nhnacademy.jdbc.board.post.domain;

import java.sql.Timestamp;
import lombok.Value;

@Value
public class Post {
    long postNum;
    String title;
    String content;
    String writerId;
    String modifierId;
    Timestamp writeTime;
    Timestamp modifyTime;
    boolean visibility;
}
