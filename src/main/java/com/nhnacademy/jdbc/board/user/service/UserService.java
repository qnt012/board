package com.nhnacademy.jdbc.board.user.service;

import com.nhnacademy.jdbc.board.user.domain.User;

import java.util.Optional;

/**
 * @Author : marco@nhnacademy.com
 * @Date : 17/05/2022
 */
public interface UserService {
     Optional<User> getUser(long id);
}
