package com.xie.java.xsd.defined.demo1;

/*
 * 1.通过xsd 自定标签spring 注入的类
 */
public class User {
    private String userName;
    private String email;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}