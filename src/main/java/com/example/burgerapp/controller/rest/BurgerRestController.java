package com.example.burgerapp.controller.rest;

import com.example.burgerapp.domain.models.burger.BurgerVenueInfo;
import com.example.burgerapp.domain.models.venue.VenueResult;
import com.example.burgerapp.services.BurgerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class BurgerRestController {
    @Autowired
    BurgerService burgerService;

    @GetMapping
    public ResponseEntity<List<BurgerVenueInfo>> getBurgerJointsInTallinn() {
        VenueResult venueResult = burgerService.getVenues();
        List<BurgerVenueInfo> burgerVenueInfos = new ArrayList<>();

        return new ResponseEntity<>(burgerService.getBurgerVenuesInfo(venueResult, burgerVenueInfos), HttpStatus.OK);
    }
}
