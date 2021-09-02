package com.challenge_2021.challenge_2021.models.entitys;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.challenge_2021.challenge_2021.models.DTOs.SatelliteInOrbit;

@Entity
@Table(name = "satellites")
public class Satellite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer id;
    private String name_satellite;
    private Float distance;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "satellite_id")
    private List<Word> message_satellite;
    public String getName_satellite() {
        return name_satellite;
    }

    public void setName_satellite(String name_satellite) {
        this.name_satellite = name_satellite;
    }

    public List<Word> getMessage_satellite() {
        return message_satellite;
    }

    public void setMessage_satellite(List<Word> message_satellite) {
        this.message_satellite = message_satellite;
    }

    public static List<SatelliteInOrbit> getSatellitesinorbit() {
        return satellitesInOrbit;
    }
    static final List<SatelliteInOrbit> satellitesInOrbit =  new ArrayList<SatelliteInOrbit>(Arrays.asList(
        new SatelliteInOrbit("kenobi", new Coordinates(-500F,-200F)), 
        new SatelliteInOrbit("skywalker", new Coordinates(100F,-100F)),
        new SatelliteInOrbit("sato", new Coordinates(500F,100F))));

    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameSatellite() {
        return name_satellite;
    }
    public void setNameSatellite(String name) {
        this.name_satellite = name;
    }
    public Float getDistance() {
        return distance;
    }
    public void setDistance(Float distance) {
        this.distance = distance;
    }
    public List<Word> getMessageSatellite() {
        return message_satellite;
    }
    public void setMessageSatellite(List<Word> listWord) {
        this.message_satellite = listWord;
    }
    public List<SatelliteInOrbit> getListSatelliteInOrbit() {
        return satellitesInOrbit;
    }
    
}
