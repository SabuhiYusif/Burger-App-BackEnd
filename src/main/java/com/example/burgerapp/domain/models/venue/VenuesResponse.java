package com.example.burgerapp.domain.models.venue;

import lombok.Data;

import java.util.List;

@Data
public class VenuesResponse {
    private List<Venue> venues;
}
