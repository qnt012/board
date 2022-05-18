package com.nhnacademy.jdbc.board.post;

import java.time.LocalDateTime;

public class Post {
    private final long postNum;
    private final long boardNum;
    private final long writerNum;
    private long modifierNum;
    private final long parentPostNum;
    private final String title;
    private final String content;
    private final LocalDateTime writerTime;
    private final LocalDateTime modifyTime;
    private final long depth;
    private boolean visibility;

    public Post(long postNum, long boardNum, long writerNum, long modifierNum,
                long parentPostNum,
                String title, String content, LocalDateTime writerTime,
                LocalDateTime modifyTime, long depth, boolean visibility) {
        this.postNum = postNum;
        this.boardNum = boardNum;
        this.writerNum = writerNum;
        this.modifierNum = modifierNum;
        this.parentPostNum = parentPostNum;
        this.title = title;
        this.content = content;
        this.writerTime = writerTime;
        this.modifyTime = modifyTime;
        this.depth = depth;
        this.visibility = visibility;
    }
}
