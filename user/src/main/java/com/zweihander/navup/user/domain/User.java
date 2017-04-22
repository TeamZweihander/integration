package com.zweihander.navup.user.domain;


public class User
{
    String name;
    String email;

    public User(String name_, String email_){
        this.name = name_;
        this.email = email_;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name_){
        this.name = name_;
    }

    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email_){
        this.email = email_;
    }

}
