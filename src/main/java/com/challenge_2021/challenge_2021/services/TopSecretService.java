package com.challenge_2021.challenge_2021.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.challenge_2021.challenge_2021.models.DTOs.SatelliteInOrbit;
import com.challenge_2021.challenge_2021.models.entitys.Coordinates;
import com.challenge_2021.challenge_2021.models.entitys.Position;
import com.challenge_2021.challenge_2021.models.entitys.Satellite;
import com.challenge_2021.challenge_2021.models.entitys.Word;
import com.challenge_2021.challenge_2021.models.responses.ResponseTopSecret;
import com.challenge_2021.challenge_2021.repositories.SatellitesRepository;
import com.challenge_2021.challenge_2021.repositories.WordsReopository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TopSecretService {
    @Autowired
    SatellitesRepository satellitesRepository;
    @Autowired
    WordsReopository wordsRepository;

    public ResponseEntity<Object> topSecret(List<Satellite> satellites) throws Exception {
        ResponseTopSecret responseTopSecret = new ResponseTopSecret();
        
        List<List<Word>>  messagesList = new ArrayList<List<Word>>();
        for (Satellite satillite : satellites) {
            messagesList.add((List<Word>) satillite.getMessageSatellite());
        }
        responseTopSecret.setPosition(getLocation(satellites));
        responseTopSecret.setMessage(getMessage(messagesList));
        ResponseEntity<Object> response = new ResponseEntity<Object>(responseTopSecret,HttpStatus.OK);
        return  response;
    }

    public ResponseEntity<Object> saveTopSecretSplit(Satellite satellite) throws Exception {
        satellitesRepository.save(satellite);
        ResponseEntity<Object> response = new ResponseEntity<Object>(1,HttpStatus.OK);
        return  response;
    }

    public ResponseEntity<Object> getTopSecretSplit() throws Exception {
        ResponseEntity<Object> response = null;
        List<Satellite> satellites = (ArrayList<Satellite>) satellitesRepository.findAll();
        if(satellites.size() == 3) {
            response = topSecret(satellites);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "he request cannot be processed because we do not have the data of all the satellites");
        }
        return  response;
    }

    private Position getLocation(List<Satellite> satellites) throws Exception {

        List<Float> listDistances = new ArrayList<Float>();
        List<Coordinates> listCoordinates = new ArrayList<Coordinates>();
        for (Satellite satillite : satellites) {
            for(SatelliteInOrbit satelliteInOrbit : Satellite.getSatellitesinorbit()) {
                if(satillite.getNameSatellite().equals(satelliteInOrbit.getName())) {
                    listDistances.add(satillite.getDistance());
                    listCoordinates.add(satelliteInOrbit.getCoordinates());
                }
             }
        }
        Position position = getPoint(listCoordinates.get(0),listDistances.get(0),
        listCoordinates.get(1),listDistances.get(1),
        listCoordinates.get(2),listDistances.get(2));
        return  position;
    }

    static Position getPoint(Coordinates point1, Float distance1,
                             Coordinates point2, Float distance2,
                             Coordinates point3, Float distance3) throws Exception {
        
        if(distance1 == null || distance2 == null || distance3 == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The data is not correct, the position could not be calculated");
        }

        Position position = new Position();
        float[] P1   = new float[2];
        float[] P2   = new float[2];
        float[] P3   = new float[2];
        float[] ex   = new float[2];
        float[] ey   = new float[2];
        float[] p3p1 = new float[2];
        float jval  = 0;
        float temp  = 0;
        float ival  = 0;
        float p3p1i = 0;
        float triptx;
        float xval;
        float yval;
        float t1;
        float t2;
        float t3;
        float t;
        float exx;
        float d;
        float eyy;

        //point 1
        P1[0] = point1.getX();
        P1[1] = point1.getY();
        //point 2
        P2[0] = point2.getX();
        P2[1] = point2.getY();
        //point 3
        P3[0] = point3.getX();
        P3[1] = point3.getY();

        
        distance1 = (distance1 / 100000);
        
        distance2 = (distance2 / 100000);
        
        distance3 = (distance3 / 100000);

        for (int i = 0; i < P1.length; i++) {
            t1   = P2[i];
            t2   = P1[i];
            t    = t1 - t2;
            temp += (t*t);
        }
        d = (float) Math.sqrt(temp);
        for (int i = 0; i < P1.length; i++) {
            t1    = P2[i];
            t2    = P1[i];
            exx   = (float) ((t1 - t2)/(Math.sqrt(temp)));
            ex[i] = exx;
        }
        for (int i = 0; i < P3.length; i++) {
            t1      = P3[i];
            t2      = P1[i];
            t3      = t1 - t2;
            p3p1[i] = t3;
        }
        for (int i = 0; i < ex.length; i++) {
            t1 = ex[i];
            t2 = p3p1[i];
            ival += (t1*t2);
        }
        for (int  i = 0; i < P3.length; i++) {
            t1 = P3[i];
            t2 = P1[i];
            t3 = ex[i] * ival;
            t  = t1 - t2 -t3;
            p3p1i += (t*t);
        }
        for (int i = 0; i < P3.length; i++) {
            t1 = P3[i];
            t2 = P1[i];
            t3 = ex[i] * ival;
            eyy = (float) ((t1 - t2 - t3)/Math.sqrt(p3p1i));
            ey[i] = eyy;
        }
        for (int i = 0; i < ey.length; i++) {
            t1 = ey[i];
            t2 = p3p1[i];
            jval += (t1*t2);
        }
        xval = (float) ((Math.pow(distance1, 2) - Math.pow(distance2, 2) + Math.pow(d, 2))/(2*d));
        yval = (float) (((Math.pow(distance1, 2) - Math.pow(distance3, 2) + Math.pow(ival, 2) + Math.pow(jval, 2))/(2*jval)) - ((ival/jval)*xval));

        t1 = point1.getX();
        t2 = ex[0] * xval;
        t3 = ey[0] * yval;
        triptx = t1 + t2 + t3;
        position.setX((float) triptx);
        t1 = point1.getY();
        t2 = ex[1] * xval;
        t3 = ey[1] * yval;
        triptx = t1 + t2 + t3;
        position.setY((float) triptx);
        return position;
    }

    private String getMessage(List<List<Word>> messagesList) throws Exception {
        messagesList =  normalizeArrays(messagesList);
        List<Word> messageList = retriveMessage(messagesList);
        String message = buildMessage(messageList);
        return message;
    }

    private List<List<Word>> normalizeArrays(List<List<Word>> messagesList) {

        //I get the size of the smallest array
        Integer sizeOfTheSmallestArray  = 99999;
        for(List<Word> messageList : messagesList) {
            if(messageList.size() < sizeOfTheSmallestArray) {
                sizeOfTheSmallestArray = messageList.size();
            }
        }
        
        //I remove the outdated objects
        for(List<Word> messageList : messagesList) {
            Boolean isNotEmptyFlag = false;
            Iterator<Word> iter = messageList.iterator();
            while (iter.hasNext()) {
                Word item = iter.next();
                if(messageList.size() > sizeOfTheSmallestArray){
                    if(item.getWord().equals("") && !isNotEmptyFlag){
                        iter.remove();
                    } else {
                        isNotEmptyFlag = true;
                    }
                }
            }
        }
        return messagesList;
    }

    private List<Word> retriveMessage(List<List<Word>> messagesList) throws Exception {
        Integer index = 0;
        while((messagesList.size() - 1) > index) {
            List<Word> firstList = messagesList.get(index);
            List<Word> secondList = messagesList.get(index +1);
            for(Integer i = 0; i < messagesList.get(index).size(); i++) {
                    
                    if(firstList.get(i).getWord().equals(secondList.get(i).getWord())) {
                        secondList.set(i,firstList.get(i));
                    } else if (!firstList.get(i).getWord().equals("") && secondList.get(i).getWord().equals("")) {
                        secondList.set(i,firstList.get(i));
                    } else if (firstList.get(i).getWord().equals("") && !secondList.get(i).getWord().equals("")) {
                        secondList.set(i,firstList.get(i));
                    } else if (!firstList.get(i).getWord().equals("") && !secondList.get(i).getWord().equals("")) {
                        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The message could not be processed, the data does not have a standard format");
                    }
            }
            messagesList.set(index + 1, secondList);
            index++;
        }

        return messagesList.get(index);
    }

    private String buildMessage(List<Word> messageList) {
        String message = "";
        for(Word world : messageList) {
            if(!message.equals("")) {
                message = message + " ";
            }
            message = message + world.getWord();
        }
        return message;
    }

}