package com.nhnacademy.jdbc.board.post.domain;

import java.sql.Timestamp;
import lombok.Value;

@Value
public class PostView {
    long postNum;
    String title;
    String writerId;
    Timestamp writeTime;
    int commentCount;
}
