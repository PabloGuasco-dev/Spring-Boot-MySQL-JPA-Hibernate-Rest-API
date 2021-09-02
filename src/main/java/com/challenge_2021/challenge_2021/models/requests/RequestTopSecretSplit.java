package com.challenge_2021.challenge_2021.models.requests;

import java.util.List;

public class RequestTopSecretSplit {
    private Float distance;
    private List<String> message;
    public Float getDistance() {
        return distance;
    }
    public void setDistance(Float distance) {
        this.distance = distance;
    }
    public List<String> getMessage() {
        return message;
    }
    public void setMessage(List<String> message) {
        this.message = message;
    }
}
