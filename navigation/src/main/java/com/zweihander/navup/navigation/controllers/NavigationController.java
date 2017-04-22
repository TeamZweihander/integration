package com.zweihander.navup.navigation.controllers;

import java.util.LinkedList;
import java.util.List;

import com.zweihander.navup.navigation.domain.Location;
import com.zweihander.navup.navigation.domain.Route;
import com.zweihander.navup.navigation.service.NavigationService;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@RestController
public class NavigationController
{
    private final NavigationService navigationService = new NavigationService();

    @RequestMapping(value ="/addLocation", method = RequestMethod.POST)
    public ResponseEntity addLocation(Location location) {
            navigationService.addLocation(location);
            return ResponseEntity.ok("Location added to database");
    }

    @RequestMapping(value = "/modifyLocation", method = RequestMethod.PUT)
    public ResponseEntity modifyLocation(Location location){
        navigationService.modifyLocation(location);
        return ResponseEntity.ok("Location modified");
    }

    @RequestMapping(value = "/removeLocation", method = RequestMethod.DELETE)
    public ResponseEntity removeLocation(Location location){
        navigationService.deleteLocation(location);
        return ResponseEntity.ok("Location deleted");
    }


    @RequestMapping(value = "/addRoute", method = RequestMethod.POST)
    public ResponseEntity addRoute(Route route){
        navigationService.saveRoute(route);
        return ResponseEntity.ok("Route saved");
    }

    @RequestMapping(value = "/modifyRoute", method = RequestMethod.PUT)
    public ResponseEntity modifyRoute(String routeId){
        navigationService.deleteRoute(routeId);
        return ResponseEntity.ok("Route modified");
    }

    @RequestMapping(value = "/removeRoute", method = RequestMethod.DELETE)
    public ResponseEntity removeRoute(String  routeId){
        navigationService.deleteRoute(routeId);
        return ResponseEntity.ok("Route deleted");
    }

}
