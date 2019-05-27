package com.example.burgerapp.services.impl;

import com.example.burgerapp.domain.models.burger.BurgerResponse;
import com.example.burgerapp.domain.models.venue.Venue;
import com.example.burgerapp.domain.models.venue.VenueResult;
import com.example.burgerapp.services.BurgerRecognizeService;
import com.example.burgerapp.services.BurgerService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Service
public class BurgerServiceImpl implements BurgerService {
    private static final String TALLINN_BURGER_VENUES_URL = "https://api.foursquare.com/v2/venues/search?client_id=YH030505ZV4ZCUQD0BERJNSRETWNCZWP0PEZNCST5QEU3APO&client_secret=05EFSURPYKAYYB0M2BEPWMP5JSDOUX50252HJPSMWRT0VYU0&v=20190425&near=tallinn&intent=browse&radius=2000&query=burger joint&limit=10";
    private static final String BASE_PLACE_API_URL = "https://foursquare.com/v/burger/";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    BurgerService burgerService;

    @Autowired
    BurgerRecognizeService burgerRecognizeService;

    @Override
    public VenueResult getVenues() {
        return restTemplate.getForObject(TALLINN_BURGER_VENUES_URL, VenueResult.class);
    }

    @Override
    public StringBuilder getPhotoUrls(String url) {
        StringBuilder photoUrls = new StringBuilder();
        try {
            Document doc = Jsoup.connect(url).get();
            doc.select("img").forEach(link -> {
                if (link.toString().contains("600x600")) {
                    String startUrl = link.toString().substring(link.toString().indexOf("\"") + 1);
                    String fullUrl = startUrl.substring(0, startUrl.indexOf("\""));

                    photoUrls.append(fullUrl);
                    photoUrls.append(",");
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }

        return photoUrls;
    }

    /**
     * Fills burgerJointsMap HashMap with name address and burger photo url
     * If burger photo not found return null
     * Key is comma seperated name and address of venue respectively
     * Value is url with burger in it
     * @param venueResult
     * @param burgerJointsMap
     * @return
     */
    @Override
    public HashMap<String, String> fillBurgerJointsMapWithVenueInfo(VenueResult venueResult, HashMap<String, String> burgerJointsMap) {
        for (Venue venue : venueResult.getResponse().getVenues()) {
            String url = BASE_PLACE_API_URL + venue.getId() + "/" + "photos";
            StringBuilder photoUrls = burgerService.getPhotoUrls(url);
            BurgerResponse res = burgerRecognizeService.getUrlWithBurger(photoUrls, burgerJointsMap, url);
            if (res != null) {
                burgerJointsMap.put(venue.getName() + "," + venue.getLocation().getAddress(), res.getUrlWithBurger());
            }else{
                if (venue.getLocation().getAddress() == null){
                    burgerJointsMap.put(venue.getName() + "," + "Not Found", null);
                } else {
                    burgerJointsMap.put(venue.getName() + "," + venue.getLocation().getAddress(), null);
                }

            }
        }

        return burgerJointsMap;
    }
}
