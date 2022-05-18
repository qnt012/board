package com.nhnacademy.jdbc.board.user.domain;

/**
 * @Author : marco@nhnacademy.com
 * @Date : 17/05/2022
 */
public class User {
    private final int userNum;
    private final String userId;
    private final String userPassword;

    public int getUserNum() {
        return userNum;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public User(int userNum, String userId, String userPassword) {
        this.userNum = userNum;
        this.userId = userId;
        this.userPassword = userPassword;
    }

    @Override
    public String toString() {
        return "User{" +
            "user_num=" + userNum +
            ", user_id='" + userId + '\'' +
            ", user_pw='" + userPassword + '\'' +
            '}';
    }

}
