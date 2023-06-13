package com.whp.tutorials.service;

import com.whp.tutorials.model.Tutorial;

import java.util.List;
import java.util.Optional;

public interface TutorialService {
    List<Tutorial> findAll();

    List<Tutorial> findByTitleContainingIgnoreCase(String title);

    Optional<Tutorial> findById(long id);

    Tutorial save(Tutorial tutorial);

    void deleteById(long id);

    List<Tutorial> findByPublished(boolean b);
}
