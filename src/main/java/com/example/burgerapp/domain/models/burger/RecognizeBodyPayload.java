package com.example.burgerapp.domain.models.burger;

import lombok.Data;

import java.util.List;

@Data
public class RecognizeBodyPayload {
    private List<String> urls;
}
