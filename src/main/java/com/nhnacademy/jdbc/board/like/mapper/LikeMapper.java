package com.nhnacademy.jdbc.board.like.mapper;

import com.nhnacademy.jdbc.board.like.domain.Like;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LikeMapper {
    Optional<Like> selectLike(@Param("userNum") long userNum, @Param("postNum") long postNum);
    void insertLike(@Param("userNum") long userNum, @Param("postNum") long postNum);
    void deleteLike(@Param("userNum") long userNum, @Param("postNum") long postNum);
}
