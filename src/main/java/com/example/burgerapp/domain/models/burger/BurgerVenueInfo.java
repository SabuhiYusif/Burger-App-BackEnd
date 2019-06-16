package com.example.burgerapp.domain.models.burger;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class BurgerVenueInfo {

    private String name;
    private String address;
    private String image;

}
