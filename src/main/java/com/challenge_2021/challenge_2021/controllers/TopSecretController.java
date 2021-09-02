package com.challenge_2021.challenge_2021.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.List;

import com.challenge_2021.challenge_2021.models.DTOs.SatelliteDTO;
import com.challenge_2021.challenge_2021.models.entitys.Satellite;
import com.challenge_2021.challenge_2021.models.entitys.Word;
import com.challenge_2021.challenge_2021.models.requests.RequestTopSecret;
import com.challenge_2021.challenge_2021.models.requests.RequestTopSecretSplit;
import com.challenge_2021.challenge_2021.services.TopSecretService;


@RestController
@RequestMapping()
public class TopSecretController {
     @Autowired
     TopSecretService topSecretService;

    @PostMapping("/topsecret")
    public ResponseEntity<Object> topsecret(@RequestBody RequestTopSecret requestTopSecret) throws Exception { 
         try {
            ArrayList<Satellite> satellites = new ArrayList<Satellite>();
            for(SatelliteDTO satelliteDTO : requestTopSecret.getSatellites()) {
                Satellite satellite = new Satellite();
                List<Word> listWord = new ArrayList<Word>();
                for(String word : satelliteDTO.getMessage()) {
                    Word newWord = new Word(word);
                    listWord.add(newWord);
                }
                satellite.setMessageSatellite(listWord);
                satellite.setDistance(satelliteDTO.getDistance());
                satellite.setNameSatellite(satelliteDTO.getName());
                satellites.add(satellite);
            }
            return  topSecretService.topSecret(satellites);
        } catch (ResponseStatusException e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
            //return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
            //return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/topsecret_split/{nameSatellite}")
    public ResponseEntity<Object> topsecret_split_post(@PathVariable String nameSatellite, @RequestBody RequestTopSecretSplit requestTopSecretSplit) throws Exception { 
         try {
             Satellite satellite = new Satellite();
            satellite.setNameSatellite(nameSatellite);
            satellite.setDistance(requestTopSecretSplit.getDistance());
            List<Word> listWord = new ArrayList<Word>();
            for(String word : requestTopSecretSplit.getMessage()) {
                Word newWord = new Word();
                newWord.setWord(word);
                listWord.add(newWord);
            }
            satellite.setMessageSatellite(listWord);
            return  topSecretService.saveTopSecretSplit(satellite);
        } catch (ResponseStatusException e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
            //return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/topsecret_split")
    public ResponseEntity<Object> topsecret_split_get() throws Exception { 
         try {
            return  topSecretService.getTopSecretSplit();
        } catch (ResponseStatusException e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
            //return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}

