package com.nhnacademy.jdbc.board.user.domain;

/**
 * @Author : marco@nhnacademy.com
 * @Date : 17/05/2022
 */
public class User {
    private final int user_num;
    private final String user_id;
    private final String user_pw;

    public int getUser_num() {
        return user_num;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getUser_pw() {
        return user_pw;
    }

    public User(int user_num, String user_id, String user_pw) {
        this.user_num = user_num;
        this.user_id = user_id;
        this.user_pw = user_pw;
    }

    @Override
    public String toString() {
        return "User{" +
            "user_num=" + user_num +
            ", user_id='" + user_id + '\'' +
            ", user_pw='" + user_pw + '\'' +
            '}';
    }

}
