package com.xie.java.xsd.defined.demo1;

public class User {
    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    private String userName;
    private String email;

    private School school;


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