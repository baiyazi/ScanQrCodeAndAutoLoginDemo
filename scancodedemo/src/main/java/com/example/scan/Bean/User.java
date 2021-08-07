package com.example.scan.Bean;


import java.util.Objects;

public class User {
    private String name;
    private String userID;

    public String getName() {
        return name;
    }

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", userID='" + userID + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) &&
                Objects.equals(userID, user.userID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, userID);
    }

    public User(String name, String userID) {
        this.name = name;
        this.userID = userID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
