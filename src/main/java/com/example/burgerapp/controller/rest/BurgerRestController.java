package com.example.burgerapp.controller.rest;

import com.example.burgerapp.domain.models.venue.VenueResult;
import com.example.burgerapp.services.BurgerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@CrossOrigin
public class BurgerRestController {
    @Autowired
    BurgerService burgerService;

    @GetMapping
    public HashMap<String, String> getBurgerJointsInTallinn() {
        VenueResult venueResult = burgerService.getVenues();
        HashMap<String, String> burgerJointsMap = new HashMap<>();

        return burgerService.fillBurgerJointsMapWithVenueInfo(venueResult, burgerJointsMap);
    }
}
