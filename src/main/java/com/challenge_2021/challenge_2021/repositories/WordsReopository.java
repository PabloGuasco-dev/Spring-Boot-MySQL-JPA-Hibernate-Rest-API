package com.challenge_2021.challenge_2021.repositories;

import com.challenge_2021.challenge_2021.models.entitys.Word;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WordsReopository extends CrudRepository<Word, Integer> {
    
}
