package com.zweihander.navup.navigation.controllers;

import java.util.LinkedList;
import java.util.List;

import com.zweihander.navup.navigation.domain.POI;
import com.zweihander.navup.navigation.service.NavigationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@RestController
public class NavigationController
{
    @Autowired
    NavigationService navigationService;

    @RequestMapping(value ="/addPOI", method = RequestMethod.POST)
    public ResponseEntity addLocation(POI poi) {
            navigationService.addPOI(poi);
            return ResponseEntity.ok("POI added to database");
    }

    @RequestMapping(value = "/modifyPOI", method = RequestMethod.PUT)
    public ResponseEntity modifyLocation(POI poi){
        navigationService.modifyPOI( poi);
        return ResponseEntity.ok("POI modified");
    }

    @RequestMapping(value = "/removePOI", method = RequestMethod.DELETE)
    public ResponseEntity removeLocation(POI poi){
        navigationService.deletePOI(poi);
        return ResponseEntity.ok("POI deleted");
    }

    @RequestMapping(value = "/getPOI", method = RequestMethod.GET)
    public ResponseEntity getPOI(POI poi){
        navigationService.getPOI(poi.getUsername(), poi.getLongitude(), poi.getLatitude());
        return ResponseEntity.ok("retrieved POI");
    }

}
