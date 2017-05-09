package com.zweihander.navup.data.controllers;

import com.zweihander.navup.data.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class DATAController {
    // Todo: inject

    //@Autowired
   DataService dataService = new DataService();

    @RequestMapping(value = "/getLocation", method = RequestMethod.GET)
    public @ResponseBody
    String login() throws Exception {
         dataService.login();
         dataService.getLocation("dc:ob:34:f2:6e:56");
        return "Hello World!";
    }
}
