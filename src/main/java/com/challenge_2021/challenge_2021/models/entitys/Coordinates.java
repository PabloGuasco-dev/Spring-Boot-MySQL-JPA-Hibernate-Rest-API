package com.challenge_2021.challenge_2021.models.entitys;

public class Coordinates {
    private Float x;
    private Float y;
    
    public Float getX() {
        return x;
    }
    public void setX(Float x) {
        this.x = x;
    }
    public Float getY() {
        return y;
    }
    public void setY(Float y) {
        this.y = y;
    }
    public Coordinates(Float x, Float y) {
        this.x = x;
        this.y = y;
    }
}
