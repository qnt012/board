package com.nhnacademy.jdbc.board.like.domain;

import lombok.Value;

@Value
public class Like {
    long likeNum;
    long userNum;
    long postNum;
}
