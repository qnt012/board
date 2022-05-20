package com.nhnacademy.jdbc.board.post.service.impl;

import com.nhnacademy.jdbc.board.post.domain.Comment;
import com.nhnacademy.jdbc.board.post.mapper.CommentMapper;
import com.nhnacademy.jdbc.board.post.mapper.PostMapper;
import com.nhnacademy.jdbc.board.post.service.CommentService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class DefaultCommentService implements CommentService {
    private final CommentMapper commentMapper;

    public DefaultCommentService(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    @Override
    public Comment getComment(long commentNum) {
        return commentMapper.selectComment(commentNum);
    }
    @Override
    public long modifyComment(long commentNum, String commentContent) {
        commentMapper.updateComment(commentNum, commentContent);
        return commentMapper.selectPostNum(commentNum);
    }

    @Override
    public long removeComment(long commentNum) {
        long postNum = commentMapper.selectPostNum(commentNum);
        commentMapper.deleteComment(commentNum);
        return postNum;
    }
    @Override
    public void createComment(long postNum, long writerNum, String commentContent) {
        commentMapper.insertComment(postNum, writerNum, commentContent);
    }
    @Override
    public List<Comment> viewComments(long postNum) {
        return commentMapper.selectPostComments(postNum);
    }
}
