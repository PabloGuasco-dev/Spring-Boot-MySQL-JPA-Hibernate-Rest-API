package com.challenge_2021.challenge_2021.models.DTOs;

import com.challenge_2021.challenge_2021.models.entitys.Coordinates;

public class SatelliteInOrbit {
    private String name;
    private Coordinates coordinates;
    public String getName() {
        return name;
    }
    public SatelliteInOrbit(String name, Coordinates coordinates) {
        this.name = name;
        this.coordinates = coordinates;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Coordinates getCoordinates() {
        return coordinates;
    }
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }
}
