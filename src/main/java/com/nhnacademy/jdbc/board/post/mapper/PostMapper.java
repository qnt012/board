package com.nhnacademy.jdbc.board.post.mapper;

import com.nhnacademy.jdbc.board.post.domain.Post;
import com.nhnacademy.jdbc.board.post.domain.PostView;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PostMapper {
    Post selectPost(long postNum);
    List<PostView> selectPostViews(@Param("offset") int offset);
    List<PostView> selectInvisiblePostViews(@Param("offset") int offset);
    List<PostView> selectLikePostViews(@Param("userNum") long userNum, @Param("offset") int offset);
    List<PostView> selectPostViewsByKeyword(@Param("keyword") String keyword, @Param("offset") int offset);
    void insertPost(@Param("writerNum") long writerNum, @Param("title") String title, @Param("content") String content);
    void updatePost(@Param("postNum") long postNum, @Param("title") String title, @Param("content") String content, @Param("modifierNum") long modifierNum);
    void updatePostVisibility(@Param("postNum") long postNum, @Param("visibility") boolean visibility);
    long selectLastInsertId();
}
