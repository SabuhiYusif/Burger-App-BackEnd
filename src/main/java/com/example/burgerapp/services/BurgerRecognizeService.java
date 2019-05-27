package com.example.burgerapp.services;

import com.example.burgerapp.domain.models.burger.BurgerResponse;

import java.util.HashMap;

public interface BurgerRecognizeService {
    BurgerResponse getUrlWithBurger(StringBuilder photoUrls, HashMap<String, String> urls, String url);

}
