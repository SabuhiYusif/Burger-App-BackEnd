package com.example.burgerapp.services;

import com.example.burgerapp.domain.models.burger.BurgerVenueInfo;
import com.example.burgerapp.domain.models.venue.VenueResult;

import java.util.List;

public interface BurgerService {
    VenueResult getVenues();
    List<String> getPhotoUrls(String url);
    List<BurgerVenueInfo> getBurgerVenuesInfo(VenueResult venueResult, List<BurgerVenueInfo> burgerVenues);
}
