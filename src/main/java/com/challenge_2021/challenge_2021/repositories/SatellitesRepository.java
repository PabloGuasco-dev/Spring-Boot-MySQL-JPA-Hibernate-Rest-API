package com.challenge_2021.challenge_2021.repositories;

import com.challenge_2021.challenge_2021.models.entitys.Satellite;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SatellitesRepository extends CrudRepository<Satellite, Integer> {
    
}
