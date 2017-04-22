package com.zweihander.navup.user.service;

/**
 * Created by siphokazi on 2017/04/22.
 */
import com.zweihander.navup.user.domain.User;
public class UserService {

    public User getUser(String name){
        return new User(name, "test@gmail.com");
    }

    /* TODO: Add a user to the DB */
    public Boolean registerUser(User user){
        return true;
    }

    public Boolean updateUserName( String username, String userId){
        return true;
    }

    public Boolean removeUser(String userId){
        return true;
    }

}
