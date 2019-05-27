package com.example.burgerapp.services.impl;

import com.example.burgerapp.domain.models.burger.BurgerResponse;
import com.example.burgerapp.domain.models.burger.RecognizeBodyPayload;
import com.example.burgerapp.services.BurgerRecognizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Service
public class BurgerRecognizeServiceImpl implements BurgerRecognizeService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public BurgerResponse getUrlWithBurger(StringBuilder photoUrls, HashMap<String, String> urls, String url) {
        List<String> urlList = Arrays.asList(photoUrls.toString().split(","));
        RecognizeBodyPayload body = new RecognizeBodyPayload();
        BurgerResponse res = null;
        if (urlList.size() > 0){
            body.setUrls(urlList);
            try {
                res = restTemplate.postForObject("https://pplkdijj76.execute-api.eu-west-1.amazonaws.com/prod/recognize", body, BurgerResponse.class);

            }catch (Exception e){
                e.printStackTrace();
            }

        }
        return res;
    }
}
