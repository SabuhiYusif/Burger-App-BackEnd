package com.example.burgerapp.services.impl;

import com.example.burgerapp.domain.models.venue.VenueResult;
import com.example.burgerapp.services.BurgerService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BurgerServiceImpl implements BurgerService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public VenueResult getVenues() {
        final String uri = "https://api.foursquare.com/v2/venues/search?client_id=YH030505ZV4ZCUQD0BERJNSRETWNCZWP0PEZNCST5QEU3APO&client_secret=05EFSURPYKAYYB0M2BEPWMP5JSDOUX50252HJPSMWRT0VYU0&v=20190425&near=tallinn&intent=browse&radius=2000&query=burger joint&limit=10";
        VenueResult venueResult = restTemplate.getForObject(uri, VenueResult.class);
        return venueResult;
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
}
