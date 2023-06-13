package com.whp.tutorials.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.whp.tutorials.model.CacheData;
import com.whp.tutorials.model.Tutorial;
import com.whp.tutorials.repo.CacheDataRepo;
import com.whp.tutorials.repo.TutorialRepo;
import com.whp.tutorials.service.TutorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TutorialServiceImpl implements TutorialService {

    @Autowired
    TutorialRepo tutorialRepo;

    @Autowired
    CacheDataRepo cacheDataRepo;

    @Autowired
    ObjectMapper objectMapper;


    @Override
    public List<Tutorial> findAll() throws JsonProcessingException {

        Optional<CacheData> optionalCacheData = cacheDataRepo.findById("allTutorials");

        // Cache hit
        if (optionalCacheData.isPresent()) {
            String tutorialsAsString = optionalCacheData.get().getValue();
            TypeReference<List<Tutorial>> mapType = new TypeReference<List<Tutorial>>() {};
            return objectMapper.readValue(tutorialsAsString, mapType);
        } else {
            //  Cache miss
            List<Tutorial> tutorials =  tutorialRepo.findAll();
            String tutorialsAsJsonString = objectMapper.writeValueAsString(tutorials);
            CacheData cacheData = new CacheData("allTutorials", tutorialsAsJsonString);
            cacheDataRepo.save(cacheData);
            return tutorials;
        }
    }

    @Override
    public List<Tutorial> findByTitleContainingIgnoreCase(String title) {
        return tutorialRepo.findByTitleContainingIgnoreCase(title);
    }

    @Override
    public Optional<Tutorial> findById(long id) {
        return tutorialRepo.findById(id);
    }

    @Override
    public Tutorial save(Tutorial tutorial) {
        return tutorialRepo.save(tutorial);
    }

    @Override
    public void deleteById(long id) {
        tutorialRepo.deleteById(id);
    }

    @Override
    public List<Tutorial> findByPublished(boolean published) {
        return tutorialRepo.findByPublished(published);
    }
}
