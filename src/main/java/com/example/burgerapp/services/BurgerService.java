package com.example.burgerapp.services;

import com.example.burgerapp.domain.models.venue.VenueResult;

import java.util.HashMap;

public interface BurgerService {
    VenueResult getVenues();
    StringBuilder getPhotoUrls(String url);
    HashMap<String, String> fillBurgerJointsMapWithVenueInfo(VenueResult venueResult, HashMap<String,String> burgerJointsMap);
}
