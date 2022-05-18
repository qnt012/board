package com.nhnacademy.jdbc.board.user.service.impl;

import com.nhnacademy.jdbc.board.user.domain.User;
import com.nhnacademy.jdbc.board.user.mapper.StudentMapper;
import com.nhnacademy.jdbc.board.user.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @Author : marco@nhnacademy.com
 * @Date : 17/05/2022
 */

@Service
public class DefaultStudentService implements StudentService {
    private final StudentMapper studentMapper;

    public DefaultStudentService(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

    public Optional<User> getStudent(long id){
        return studentMapper.selectStudent(id);
    }
}
