package com.example.burgerapp.services.impl;

import com.example.burgerapp.domain.models.burger.BurgerResponse;
import com.example.burgerapp.domain.models.burger.BurgerVenueInfo;
import com.example.burgerapp.domain.models.venue.Venue;
import com.example.burgerapp.domain.models.venue.VenueResult;
import com.example.burgerapp.services.BurgerRecognizeService;
import com.example.burgerapp.services.BurgerService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class BurgerServiceImpl implements BurgerService {
    private static final String TALLINN_BURGER_VENUES_URL = "https://api.foursquare.com/v2/venues/search?client_id=YH030505ZV4ZCUQD0BERJNSRETWNCZWP0PEZNCST5QEU3APO&client_secret=05EFSURPYKAYYB0M2BEPWMP5JSDOUX50252HJPSMWRT0VYU0&v=20190425&near=tallinn&intent=browse&radius=2000&query=burger joint&limit=10";
    private static final String BASE_PLACE_API_URL = "https://foursquare.com/v/burger/";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private BurgerService burgerService;

    @Autowired
    private BurgerRecognizeService burgerRecognizeService;

    @Override
    public VenueResult getVenues() {
        return restTemplate.getForObject(TALLINN_BURGER_VENUES_URL, VenueResult.class);
    }

    @Override
    public List<String> getPhotoUrls(String url) {
        List<String> photoUrls = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(url).get();
            doc.select("img").forEach(link -> {
                if (link.toString().contains("600x600")) {
                    String startUrl = link.toString().substring(link.toString().indexOf("\"") + 1);
                    String fullUrl = startUrl.substring(0, startUrl.indexOf("\""));
                    photoUrls.add(fullUrl);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        return photoUrls;
    }

    @Override
    public List<BurgerVenueInfo> getBurgerVenuesInfo(VenueResult venueResult, List<BurgerVenueInfo> burgerVenues) {
        for (Venue venue : venueResult.getResponse().getVenues()) {
            String url = BASE_PLACE_API_URL + venue.getId() + "/" + "photos";
            List<String> photoUrls = burgerService.getPhotoUrls(url);
            BurgerResponse res = burgerRecognizeService.getUrlWithBurger(photoUrls);
            if (res != null) {
                BurgerVenueInfo burgerVenueInfo = BurgerVenueInfo.of(venue.getName(), venue.getLocation().getAddress(), res.getUrlWithBurger());
                burgerVenues.add(burgerVenueInfo);
            }
        }

        return burgerVenues;
    }
}
