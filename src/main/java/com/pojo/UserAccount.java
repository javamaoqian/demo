package com.pojo;

public class UserAccount {
    private String userName;
    private Integer userId;
    private Integer money;

    public UserAccount(Integer userId) {
        this.userId = userId;
    }

    public UserAccount() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "userName='" + userName + '\'' +
                ", userId=" + userId +
                ", money=" + money +
                '}';
    }
}
