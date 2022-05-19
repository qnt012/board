package com.nhnacademy.jdbc.board.post.mapper;

import com.nhnacademy.jdbc.board.post.domain.Comment;
import com.nhnacademy.jdbc.board.post.domain.Post;
import com.nhnacademy.jdbc.board.post.domain.PostView;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PostMapper {
    Post selectPost(long postNum);
    List<PostView> selectPostViews(@Param("offset") int offset);
    void insertPost(@Param("writerNum") long writerNum, @Param("title") String title, @Param("content") String content);
//    void updateNameById(String name, long id);
//    void deleteById(long id);
    int selectPostCount();
    List<Comment> selectPostComments(long postNum);
}
