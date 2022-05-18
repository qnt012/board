package com.nhnacademy.jdbc.board.post;

import java.time.LocalDateTime;

public class Post {
    private final long post_num;
    private final long board_num;
    private final long writer_num;
    private long modifier_num;
    private final long parent_post_num;
    private final String title;
    private final String content;
    private final LocalDateTime writer_time;
    private final LocalDateTime modify_time;
    private final long depth;
    private boolean visibility;

    public Post(long post_num, long board_num, long writer_num, long modifier_num,
                long parent_post_num,
                String title, String content, LocalDateTime writer_time,
                LocalDateTime modify_time, long depth, boolean visibility) {
        this.post_num = post_num;
        this.board_num = board_num;
        this.writer_num = writer_num;
        this.modifier_num = modifier_num;
        this.parent_post_num = parent_post_num;
        this.title = title;
        this.content = content;
        this.writer_time = writer_time;
        this.modify_time = modify_time;
        this.depth = depth;
        this.visibility = visibility;
    }
}
