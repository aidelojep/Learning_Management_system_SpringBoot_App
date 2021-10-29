package com.ileiwe.ileiwe.repository;

import com.ileiwe.ileiwe.model.Authority;
import com.ileiwe.ileiwe.model.LearningParty;
import com.ileiwe.ileiwe.model.Role;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Slf4j
@Sql(scripts = {"/db/insert.sql"})
class LearningPartyRepositoryTest {

    @Autowired
    LearningPartyRepository learningPartyRepository;

    @BeforeEach
    void setUp() {

    }

    @Test
    @Transactional
    @Rollback(value = false)
    void createLearningPartyTest() {
        LearningParty learningUser = new LearningParty("peter123@gmail.com", "peter123", new Authority(Role.ROLE_STUDENT));
        learningPartyRepository.save(learningUser);
        assertThat(learningUser.getId()).isNotNull();
        assertThat(learningUser.getEmail()).isEqualTo("peter123@gmail.com");
        assertThat(learningUser.getAuthorities().get(0).getAuthority()).isEqualTo(Role.ROLE_STUDENT);
        log.info("Before saving -> {}", learningUser);
        learningPartyRepository.save(learningUser);
        assertThat(learningUser.getId()).isNotNull();
        assertThat(learningUser.getEmail()).isEqualTo("peter123@gmail.com");
        assertThat(learningUser.getAuthorities().get(0).getAuthority()).isEqualTo(Role.ROLE_STUDENT);
        log.info("After saving -> {}", learningUser);
    }

    @Test
    void createLearningPartyWithExistingUniqueEmailTest() {
        //create a learning party

        LearningParty user1 = new LearningParty("aidelojepeter123@gmail.com", "1234", new Authority(Role.ROLE_STUDENT));
        log.info("Before saving {}", user1);
        //save to db
        learningPartyRepository.save(user1);
        assertThat(user1.getEmail()).isEqualTo("aidelojepeter123@gmail.com");
        assertThat(user1.getId()).isNotNull();
        //create another learning party wit same email
        LearningParty user2 = new LearningParty("aidelojepeter123@gmail.com", "1234", new Authority(Role.ROLE_INSTRUCTOR));
        assertThrows(DataIntegrityViolationException.class, () -> learningPartyRepository.save(user2));

    }
    @Test
    void learningPartyWithNullValuesTest() {
        //create a learningParty with null values
        LearningParty user2 = new LearningParty(null, null, new Authority(Role.ROLE_INSTRUCTOR));
        log.info("Before saving {}", user2);
        assertThrows(ConstraintViolationException.class,()->learningPartyRepository.save(user2));
    }

    @Test
    void learningPartyWithEmptyStringValuesTest() {
        //create a learningParty with null values
        LearningParty user = new LearningParty(" ", " ", new Authority(Role.ROLE_STUDENT));
        //save and catch exceptions
        assertThrows(ConstraintViolationException.class, () -> learningPartyRepository.save(user));
    }
    @Test
    void testThatLearningUsernameTest(){
        LearningParty learningParty = learningPartyRepository.findByEmail("peter123@yahoo.com");
        assertThat(learningParty).isNotNull();
        assertThat(learningParty.getEmail()).isEqualTo("peter123@yahoo.com");
        log.info("Learning party object () -> {}",learningParty);
    }
}