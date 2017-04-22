package com.zweihander.navup.user.controllers;

import com.zweihander.navup.user.domain.User;
import com.zweihander.navup.user.domain.UserDTO;
import com.zweihander.navup.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController
{
    private UserService userService = new UserService();
    private final Logger log = LoggerFactory.getLogger(UserController.class);

    //@GetMapping("/getUser")
    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    /* TODO: change it to UserDTO that is returned */
    public User getUser(String name){
        log.debug("Request to get user: {} ", name);
        return userService.getUser(name);
    }

//    @PostMapping("/registerUser")
    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
    /* TODO: change it to UserDTO */
    public ResponseEntity registerUser(User user){
        log.debug("Request to add user: {} ", user);
        userService.registerUser(user);
        return ResponseEntity.ok().body("User added successfully");
    }

    //@PutMapping("/updateUserName")
    @RequestMapping(value = "/updateUser", method = RequestMethod.PUT)
    public ResponseEntity updateUserName(String name, String userId){
        log.debug("Request to update user: {} ", name);
        userService.updateUserName( name,  userId);
        return ResponseEntity.ok("User Updated successfully");
    }

//    @DeleteMapping("/removeUser")
    @RequestMapping(value = "/removeUser", method = RequestMethod.DELETE)
    public ResponseEntity removeUser(String userId){
        log.debug("Request to remove user: {}", userId);
        userService.removeUser(userId);
        return ResponseEntity.ok("User removed successfully");
    }
}
