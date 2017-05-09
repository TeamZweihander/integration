package com.zweihander.navup.user.controllers;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVReader;
import com.zweihander.navup.user.domain.User;
import com.zweihander.navup.user.service.UserService;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {

    @Autowired
    UserService userService;


    /////////////////////////////////////////////////
    @Autowired
    HttpServletRequest request;


    @RequestMapping(value = "/uploadfile", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseEntity handleFileUpload(@RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                String uploadsDir = "/uploads/";
                String realPathtoUploads = request.getServletContext().getRealPath(uploadsDir);
                if (!new File(realPathtoUploads).exists()) {
                    new File(realPathtoUploads).mkdir();
                }

                //log.info("realPathtoUploads = {}", realPathtoUploads);


                String orgName = file.getOriginalFilename();
                String filePath = realPathtoUploads + orgName;
                File dest = new File(filePath);
                file.transferTo(dest);
            }
        }
    }









    ///////////////////////////////////////////////////





    // Method to create user record in database

    @RequestMapping(value = "/registerUser" , method = RequestMethod.POST)
    public ResponseEntity<User> createUser(@RequestBody User user)
    {
        user = userService.save(user);
        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }

    //Method to update user record in database

    @RequestMapping(value = "/updateUser" , method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@RequestBody User user)
    {
        User temp = userService.getBy(user.getUsername());
        if (temp != null)
        {
            user = userService.update(user);
            return new ResponseEntity<User>(temp, HttpStatus.OK);
        }
        return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
    }

    //Method to retrieve user record identified using the 'username' field from database

    @RequestMapping(value = "/findUser/{username}", method = RequestMethod.GET)
    public ResponseEntity<User> findUser(@PathVariable("username") String username)
    {
        User user = userService.getBy(username);
        if (user != null)
        {
            return new ResponseEntity<User>(user, HttpStatus.OK);
        }
        return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
    }

    // Method to delete user record identified by 'username' field from database

    @RequestMapping(value = "/deleteUser/{username}", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteUser(@PathVariable("username") String username)
    {
        User user = userService.getBy(username);
        if(user != null)
        {
            userService.deleteBy(username);
            return new ResponseEntity<User>(HttpStatus.OK);
        }
        return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
    }

    //---------------------------------------------------------------------------------------------------

    // Method for bulk creation of users

    @RequestMapping(value = "/registerUsers" , method = RequestMethod.POST)
    public ResponseEntity<List<User>> createUsers(@RequestBody MultipartFile csvFile)
    {
        List<User> users = readCSV(csvFile);

        users = userService.save(users);
        return new ResponseEntity<List<User>>(users, HttpStatus.CREATED);
    }

    // Method for bulk deletion of users

    @RequestMapping(value = "/deleteUsers", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteUsers(@RequestBody MultipartFile csvFile)
    {


        List<User> users = readCSV(csvFile);

        Iterator<User> iterator = users.listIterator();
        while(iterator.hasNext())
        {
            if(userService.getBy(iterator.next().getUsername()) == null)
            {
                return new ResponseEntity<User>(iterator.next(),HttpStatus.NOT_FOUND);
            }
        }
        userService.deleteBy(users);
        return new ResponseEntity<User>(HttpStatus.OK);
    }

    //Method to retrieve all user records from database

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public ResponseEntity <List<User>> findAll()
    {
        List<User> users = userService.getAll();
        if (users != null)
        {
            return new ResponseEntity<List<User>>(users, HttpStatus.OK);
        }
        return new ResponseEntity<List<User>>(HttpStatus.NOT_FOUND);
    }

    //---------------------------------------------------------------------------------------------------

    // Method to check if username and password combo are correct

    @RequestMapping(value = "/authenticate" , method = RequestMethod.POST)
    public ResponseEntity<String> testLog(@RequestBody User user)
    {
        User temp = userService.getBy(user.getUsername());
        if (temp != null)
        {
            if(userService.authenticate(user.getUsername(), user.getPassword()))
            {
                return new ResponseEntity<String>("Passwords Match", HttpStatus.ACCEPTED);
            }
            else
            {
                return new ResponseEntity<String>("Passwords Do Not Match ", HttpStatus.FORBIDDEN);
            }
        }
        return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
    }

    // Method to retrieve user record identified by 'id' field from database

    @RequestMapping(value = "/getById/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> getUser(@PathVariable("id") Long id)
    {
        User user = userService.getBy(id);
        if (user == null)
        {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    // Method to delete user record identified by 'id' field from database

    @RequestMapping(value = "/deleteById/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteById(@PathVariable("id") Long id)
    {
        User user = userService.getBy(id);
        if(user != null)
        {
            userService.deleteBy(id);
            return new ResponseEntity<User>(HttpStatus.OK);
        }
        return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
    }

    public List<User> readCSV(MultipartFile csvFile)
    {

        CSVReader reader = null;
        List<User> csvContent = new ArrayList<User>();

        try{
            reader = new CSVReader(new FileReader(multipartToFile(csvFile).getPath()));
            String[] line;

            while ((line = reader.readNext()) != null){
                csvContent.add(new User(line[0], line[1], line[2], line[3], line[4], Integer.parseInt(line[5]), Boolean.parseBoolean(line[6]), Boolean.parseBoolean(line[7])));
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public File multipartToFile(MultipartFile multipart) throws IllegalStateException, IOException
    {
        File convFile = new File( multipart.getOriginalFilename());
        multipart.transferTo(convFile);
        return convFile;
    }
}
