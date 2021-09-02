package com.challenge_2021.challenge_2021.models.requests;

import java.util.ArrayList;

import com.challenge_2021.challenge_2021.models.DTOs.SatelliteDTO;


public class RequestTopSecret {
    private ArrayList<SatelliteDTO> satellites;

    public ArrayList<SatelliteDTO> getSatellites() {
        return satellites;
    }

    public void setSatellites(ArrayList<SatelliteDTO> satellites) {
        this.satellites = satellites;
    }
}
