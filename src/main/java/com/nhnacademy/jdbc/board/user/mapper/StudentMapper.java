package com.nhnacademy.jdbc.board.user.mapper;

import com.nhnacademy.jdbc.board.user.domain.User;

import java.util.List;
import java.util.Optional;

public interface StudentMapper {
    Optional<User> selectStudent(long id);
    List<User> selectStudents();
    void insertStudent(User user);
    void updateNameById(String name, long id);
    void deleteById(long id);
}
