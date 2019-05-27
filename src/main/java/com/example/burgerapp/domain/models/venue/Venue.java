package com.example.burgerapp.domain.models.venue;

import com.example.burgerapp.domain.models.venue.location.Location;
import lombok.Data;

@Data
public class Venue {
    private String id;
    private String name;
    private Location location;
}
