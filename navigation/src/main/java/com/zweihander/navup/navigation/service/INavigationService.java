package com.zweihander.navup.navigation.service;

import com.zweihander.navup.navigation.domain.POI;

import java.util.ArrayList;

/**
 * Created by siphokazi on 2017/05/06.
 */
public interface INavigationService {

    public POI addPOI(POI location);

    public POI modifyPOI(POI location);

    public void deleteByID(Long id) ;

    public void deletePOI(POI poi);

    public POI getPOI(String username,double long_, double lat_);

    public ArrayList<POI> getAllPOIForUser(String username);

}
