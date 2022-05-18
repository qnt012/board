package com.nhnacademy.jdbc.board.user.mapper;

import com.nhnacademy.jdbc.board.user.domain.User;

import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    Optional<User> selectUser(long id);
    List<User> selectUsers();
    void insertUser(User user);
    void updateNameById(String name, long id);
    void deleteById(long id);
}
