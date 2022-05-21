package com.nhnacademy.jdbc.board.file.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface FileMapper {
    void insertFile(@Param("postNum") long postNum, @Param("fileName") String fileName);
}
