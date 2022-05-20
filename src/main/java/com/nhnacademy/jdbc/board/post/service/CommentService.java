package com.nhnacademy.jdbc.board.post.service;

import com.nhnacademy.jdbc.board.post.domain.Comment;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface CommentService {
    List<Comment> viewComments(long postNum);
    Comment getComment(long commentNum);
    long modifyComment(long commentNum, String commentContent);
    long removeComment(long commentNum);
    void createComment(long postNum, long writerNum, String commentContent);
}
