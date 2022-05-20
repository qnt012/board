package com.nhnacademy.jdbc.board.post.mapper;

import com.nhnacademy.jdbc.board.post.domain.Comment;
import com.nhnacademy.jdbc.board.post.domain.Post;
import com.nhnacademy.jdbc.board.post.domain.PostView;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CommentMapper {
    List<Comment> selectPostComments(long postNum);
    Comment selectComment(long commentNum);
    void insertComment(@Param("postNum") long postNum, @Param("writerNum") long writerNum, @Param("commentContent") String commentContent);
    void updateComment(@Param("commentNum") long commentNum, @Param("commentContent") String commentContent);
    void deleteComment(long commentNum);
    int selectPostNum(long commentNum);
}
