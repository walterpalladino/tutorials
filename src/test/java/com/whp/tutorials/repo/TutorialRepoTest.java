package com.whp.tutorials.repo;

import com.whp.tutorials.model.Tutorial;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class TutorialRepoTest {

    @Autowired
    TutorialRepo tutorialRepo;

    List<Long> ids=new ArrayList<>();

    @BeforeEach
    void setUp() {
        Tutorial t ;
        ids.clear();

        t = new Tutorial(null, "Java Basic Tutorial", "Java Basic Tutorial Description", true);
        t=tutorialRepo.save(t);
        ids.add(t.getId());

        t = new Tutorial(null, "Java Advanced Tutorial", "Java Advanced Tutorial Description", false);
        t=tutorialRepo.save(t);
        ids.add(t.getId());

        t = new Tutorial(null, "Python Basic Tutorial", "Python Basic Tutorial Description", true);
        t=tutorialRepo.save(t);
        ids.add(t.getId());

        t = new Tutorial(null, "Angular Tutorial", "Angular Tutorial Description", false);
        t=tutorialRepo.save(t);
        ids.add(t.getId());

        t = new Tutorial(null, "Deletable Tutorial", "Deletable Tutorial Description", false);
        t=tutorialRepo.save(t);
        ids.add(t.getId());
    }

    @AfterEach
    void tearDown() {
        tutorialRepo.deleteAll();
    }

    @Test
    void findAll() {
        List<Tutorial> results = tutorialRepo.findAll();

        assertThat(results).isNotEmpty();
        assertThat(results.size()).isEqualTo(ids.size());
    }

    @Test
    void findByTitleContainingIgnoreCase() {
        List<Tutorial> results = tutorialRepo.findByTitleContainingIgnoreCase("java");

        assertThat(results).isNotEmpty();
        assertThat(results.size()).isEqualTo(2);
        assertThat(results).filteredOn(tutorial -> tutorial.getTitle().toLowerCase().indexOf("java") > -1);
    }

    @Test
    void findById() {
        Optional<Tutorial> result = tutorialRepo.findById(ids.get(3));

        assertThat(result.isPresent());
        assertThat(result.get().getTitle().toLowerCase().indexOf("angular") > -1);
    }

    @Test
    void save() {
    }

    @Test
    void deleteById() {
        Long id = ids.get(4);
        tutorialRepo.deleteById(id);
        Optional<Tutorial> result = tutorialRepo.findById(id);
        assertThat(result.isEmpty());
    }

    @Test
    void findByPublished() {
        List<Tutorial> results = tutorialRepo.findByPublished(true);
        assertThat(results).isNotEmpty();
        assertThat(results.size()).isEqualTo(2);
    }
}