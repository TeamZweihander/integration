package com.zweihander.navup.user.controllers;

import com.zweihander.navup.user.domain.User;
import com.zweihander.navup.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    // Method to create user record in database

    @RequestMapping(value = "/createUser",method = RequestMethod.POST)
    public ResponseEntity<User> addUser(@RequestBody User user){

        user = userService.save(user);
        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }

    //Method to update user record in database

    @RequestMapping(value= "/updateUser", method = RequestMethod.PUT)
    public ResponseEntity<User> update(@RequestBody User user){

        user = userService.save(user);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    // Method to retrieve user record identified by 'id' field from database

    @RequestMapping(value = "/getById/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> getUser(@PathVariable("id") Long id)
    {
        User user = userService.getById(id);
        if (user == null)
        {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    //Method to retrieve user record identified using the 'username' field from database

    @RequestMapping(value = "/getUser/{username}", method = RequestMethod.GET)
    public ResponseEntity<User> getUserBy(@PathVariable("username") String username)
    {
        User user = userService.getBy(username);
        if (user == null)
        {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    // Method to delete user record identified by 'id' field from database

    @RequestMapping(value = "/deleteById/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteUser(@PathVariable("id") Long id)
    {
        User user = userService.getById(id);
        if(user == null)
        {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        userService.deleteById(id);
        return new ResponseEntity<User>(HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteByUsername/{username}", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteUsername(@PathVariable("username") String username)
    {
        User user = userService.getBy(username);
        if(user == null)
        {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        userService.deleteByUsername(username);
        return new ResponseEntity<User>(HttpStatus.OK);
    }
}
