package com.example.burgerapp.services.impl;

import com.example.burgerapp.domain.models.burger.BurgerResponse;
import com.example.burgerapp.domain.models.burger.RecognizeBodyPayload;
import com.example.burgerapp.services.BurgerRecognizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class BurgerRecognizeServiceImpl implements BurgerRecognizeService {
    private static final String BURGER_FINDER_URL = "https://pplkdijj76.execute-api.eu-west-1.amazonaws.com/prod/recognize";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public BurgerResponse getUrlWithBurger(List<String> photoUrls) {
        RecognizeBodyPayload body = new RecognizeBodyPayload();
        BurgerResponse burgerResponse = null;
        if (photoUrls.size() > 0){
            body.setUrls(photoUrls);
            try {
                burgerResponse = restTemplate.postForObject(BURGER_FINDER_URL, body, BurgerResponse.class);

            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return burgerResponse;
    }
}
