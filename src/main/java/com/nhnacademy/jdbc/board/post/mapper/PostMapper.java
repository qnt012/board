package com.nhnacademy.jdbc.board.post.mapper;

import com.nhnacademy.jdbc.board.post.domain.PostView;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface PostMapper {
//    Optional<Post> selectPost(long postNum);
    List<PostView> selectPostViews();
//    void insertPost(Post post);
//    void updateNameById(String name, long id);
//    void deleteById(long id);
}
