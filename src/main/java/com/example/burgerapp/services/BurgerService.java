package com.example.burgerapp.services;

import com.example.burgerapp.domain.models.venue.VenueResult;

public interface BurgerService {
    VenueResult getVenues();
    StringBuilder getPhotoUrls(String url);
}
