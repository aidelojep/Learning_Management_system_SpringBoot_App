package com.ileiwe.ileiwe.repository;

import com.ileiwe.ileiwe.model.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Slf4j
@Sql(scripts=("/db/insert.sql"))
class InstructorRepositoryTest {
//    private InstructorRepository repository;

    @Autowired
    InstructorRepository instructorRepository;

    @BeforeEach
    void setUp() {

    }

    @Test
    void saveInstructorAsLearningParty() {
        //create a learningParty
        LearningParty user = new LearningParty("trainer@ileiwe.com", "1234pass",
                new Authority(Role.ROLE_INSTRUCTOR));
        //create instructor
        Instructor instructor = Instructor.builder()
                .firstName("John")
                .lastName("Aideloje")
                .learningParty(user).build(); // map instructor with learning party

        //save instructor
        log.info("Instructor before saving {}", instructor);
        instructorRepository.save(instructor);
        assertThat(instructor.getId()).isNotNull();
        assertThat(instructor.getLearningParty().getId()).isNotNull();
        log.info("Instructor after saving -> {}", instructor);
        //save instructor

    }

    @Test
    void updateInstructorLearningPartyTest() {
        //create a learningParty
        LearningParty user = new LearningParty("trainer@ileiwe.com", "1234pass",
                new Authority(Role.ROLE_INSTRUCTOR));
        //create instructor
        Instructor instructor = Instructor.builder()
                .firstName("John")
                .lastName("Aideloje")
                .learningParty(user).build();

        //save instructor
        log.info("Instructor before saving {}", instructor);
        instructorRepository.save(instructor);
        assertThat(instructor.getId()).isNotNull();
        assertThat(instructor.getLearningParty().getId()).isNotNull();
        log.info("Instructor after saving -> {}", instructor);

        Instructor savedInstructor = instructorRepository.findById(instructor.getId()).orElse(null);
        log.info("saved Instructor -> {}", savedInstructor);
        assertThat(savedInstructor).isNotNull();
        assertThat(savedInstructor.getBio()).isNull();
        assertThat(savedInstructor.getGender()).isNull();
        savedInstructor.setBio("I am a Java Professional Programmer!");
        savedInstructor.setGender(Gender.MALE);

        instructorRepository.save(instructor);
        assertThat(instructor.getId()).isNotNull();
        assertThat(savedInstructor.getBio()).isNotNull();
        assertThat(savedInstructor.getGender()).isNotNull();
        log.info("Instructor after udating-> {}", instructor);

    }

    @Test
    void createInstructorCannotBeNull() {
        LearningParty user = new LearningParty("trainer@ileiwe.com", "1234pass",
                new Authority(Role.ROLE_INSTRUCTOR));
        Instructor instructor = Instructor.builder()
                .firstName(null)
                .lastName(null)
                .learningParty(user).build();

        assertThrows(ConstraintViolationException.class, () -> instructorRepository.save(instructor));
    }

    @Test
    void createInstructorWithEmptyValuesTest() {
        LearningParty user = new LearningParty("trainer@ileiwe.com", "1234pass",
                new Authority(Role.ROLE_INSTRUCTOR));
        Instructor instructor = Instructor.builder()
                .firstName(" ")
                .lastName(" ")
                .learningParty(user).build();
        assertThrows(ConstraintViolationException.class, () -> instructorRepository.save(instructor));

    }
}