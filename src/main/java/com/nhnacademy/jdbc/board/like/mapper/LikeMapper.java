package com.nhnacademy.jdbc.board.like.mapper;

import com.nhnacademy.jdbc.board.like.domain.Like;

import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LikeMapper {
    Optional<Like> selectLike(@Param("userNum") long userNum, @Param("postNum") long postNum);
    List<Like> selectLikes(@Param("userNum") long userNum);
    void insertLike(@Param("userNum") long userNum, @Param("postNum") long postNum);
    void deleteLike(@Param("userNum") long userNum, @Param("postNum") long postNum);
}
