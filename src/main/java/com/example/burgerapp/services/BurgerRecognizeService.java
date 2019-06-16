package com.example.burgerapp.services;

import com.example.burgerapp.domain.models.burger.BurgerResponse;

import java.util.List;

public interface BurgerRecognizeService {
    BurgerResponse getUrlWithBurger(List<String> photoUrls);

}
