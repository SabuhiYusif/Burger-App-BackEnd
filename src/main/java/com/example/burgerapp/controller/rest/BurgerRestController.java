package com.example.burgerapp.controller.rest;

import com.example.burgerapp.domain.models.burger.BurgerResponse;
import com.example.burgerapp.domain.models.venue.Venue;
import com.example.burgerapp.domain.models.venue.VenueResult;
import com.example.burgerapp.services.BurgerRecognizeService;
import com.example.burgerapp.services.BurgerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@CrossOrigin
public class BurgerRestController {
    private static final String BASE_URL = "https://foursquare.com/v/burger/";

    @Autowired
    BurgerService burgerService;

    @Autowired
    BurgerRecognizeService burgerRecognizeService;

    @GetMapping
    public HashMap<String, String> getData() {
        VenueResult venueResult = burgerService.getVenues();
        HashMap<String, String> urls = new HashMap<>();

        for (Venue venue : venueResult.getResponse().getVenues()) {
            String url = BASE_URL + venue.getId() + "/" + "photos";
            StringBuilder photoUrls = burgerService.getPhotoUrls(url);
            BurgerResponse res = burgerRecognizeService.getUrlWithBurger(photoUrls, urls, url);
            if (res != null) {
                urls.put(venue.getName() + "," + venue.getLocation().getAddress(), res.getUrlWithBurger());
            }else{
                urls.put(venue.getName() + "," + venue.getLocation().getAddress(), null);
            }
        }

        return urls;
    }
}
