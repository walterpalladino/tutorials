package com.whp.tutorials.service.impl;

import com.whp.tutorials.model.Tutorial;
import com.whp.tutorials.repo.TutorialRepo;
import com.whp.tutorials.service.TutorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TutorialServiceImpl implements TutorialService {

    @Autowired
    TutorialRepo tutorialRepo;

    @Override
    public List<Tutorial> findAll() {
        return tutorialRepo.findAll();
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
