package com.nhnacademy.jdbc.board.file.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface FileMapper {
    String selectFileName(@Param("postNum")long postNum);
    void insertFile(@Param("postNum") long postNum, @Param("fileName") String fileName);
}
