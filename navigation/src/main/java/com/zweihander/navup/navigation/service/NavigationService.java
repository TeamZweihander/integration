package com.zweihander.navup.navigation.service;

/**
 * Created by siphokazi on 2017/04/22.
 */
import com.zweihander.navup.navigation.domain.POI;
import com.zweihander.navup.navigation.repository.POIRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class NavigationService implements INavigationService{

    @Autowired
    POIRepository poiRepository;

    @Override
    public POI addPOI(POI location){
        return poiRepository.save(location);
    }

    @Override
    public POI modifyPOI(POI location){
        return poiRepository.save(location);
    }

    @Override
    public void deleteByID(Long id) {
        poiRepository.delete(id);
    }

    @Override
    public void deletePOI(POI poi){
        poiRepository.delete(poi);
    }

    @Override
    public POI getPOI(String username,double long_, double lat_){
        return poiRepository.findByUsernameAndLongitudeAndLatitude(username,long_,lat_);
    }

    @Override
    public ArrayList<POI> getAllPOIForUser(String username){
        return poiRepository.findAllByUsername(username);
    }

}