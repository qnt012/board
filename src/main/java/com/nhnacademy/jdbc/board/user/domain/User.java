package com.nhnacademy.jdbc.board.user.domain;

import java.io.Serializable;
import java.util.Objects;
import lombok.Getter;

@Getter
public class User implements Serializable{
    private final int userNum;
    private final String userId;
    private final String userPassword;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return userNum == user.userNum && Objects.equals(userId, user.userId) &&
            Objects.equals(userPassword, user.userPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userNum, userId, userPassword);
    }

    public User getUserAuthInfo() {
        return new User(userNum, userId, null);
    }
}
