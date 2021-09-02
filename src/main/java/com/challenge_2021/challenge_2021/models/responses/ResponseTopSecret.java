package com.challenge_2021.challenge_2021.models.responses;

import com.challenge_2021.challenge_2021.models.entitys.Position;

public class ResponseTopSecret {
    private Position position;
    private String message;
    
    public Position getPosition() {
        return position;
    }
    public void setPosition(Position position) {
        this.position = position;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
