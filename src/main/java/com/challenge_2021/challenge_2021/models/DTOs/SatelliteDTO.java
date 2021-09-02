package com.challenge_2021.challenge_2021.models.DTOs;

import java.util.List;

public class SatelliteDTO {
    private String name;
    private Float distance;
    private List<String> message;
    
    public SatelliteDTO(String name, Float distance, List<String> message) {
        this.name = name;
        this.distance = distance;
        this.message = message;
    }
    public SatelliteDTO() {
    }
    public String getName() {
        return name;
    }
    public void setName(String name_satellite) {
        this.name = name_satellite;
    }
    public Float getDistance() {
        return distance;
    }
    public void setDistance(Float distance) {
        this.distance = distance;
    }
    public List<String> getMessage() {
        return message;
    }
    public void setMessage(List<String> message_satellite) {
        this.message = message_satellite;
    }
}
