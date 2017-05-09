package com.zweihander.navup.navigation.controllers;

import java.util.LinkedList;
import java.util.List;

import com.zweihander.navup.navigation.domain.POI;
import com.zweihander.navup.navigation.service.NavigationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@RestController
public class NavigationController
{
    @Autowired
    NavigationService navigationService;

    @RequestMapping(value ="/addPOI", method = RequestMethod.POST)
    public ResponseEntity<POI> addLocation(@RequestBody POI poi) {
            POI result = navigationService.addPOI(poi);
            return new ResponseEntity<>(result,HttpStatus.CREATED);
    }

    @RequestMapping(value = "/modifyPOI", method = RequestMethod.PUT)
    public ResponseEntity<POI> modifyLocation(@RequestBody POI poi){

        System.out.println("=======================================");
        System.out.println("User - " + poi.getUsername());
        System.out.println("Lat - " + poi.getLatitude());
        System.out.println("Long - " + poi.getLongitude());

        POI result = navigationService.modifyPOI( poi);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @RequestMapping(value = "/deletePOI/{long}/{lat}/{username}", method = RequestMethod.DELETE)
    public ResponseEntity removeLocation(@PathVariable("username") String username, @PathVariable("long") double longitude,
                                         @PathVariable("lat") double lat){
        POI poi = navigationService.getPOI(username, longitude, lat);
        if (navigationService.deletePOI(poi)){
            return ResponseEntity.ok("POI deleted successfully");
        }
        else{
            return ResponseEntity.ok("POI NOT FOUND");
        }
    }

    @RequestMapping(value = "/getPOI/{long}/{lat}/{username}", method = RequestMethod.GET)
    public ResponseEntity<POI> getPOI(@PathVariable("username") String username, @PathVariable("long") double longitude,
                                 @PathVariable("lat") double lat){
        POI poi = navigationService.getPOI(username, longitude, lat);
        return new ResponseEntity<>(poi,HttpStatus.FOUND);
    }

}
