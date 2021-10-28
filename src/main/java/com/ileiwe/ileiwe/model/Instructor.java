package com.ileiwe.ileiwe.model;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Instructor{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  String lastName;
    private String firstName;
    private Gender gender;
    private String specialization;
    @Column(length=1000)
    private String bio;
    @OneToOne
    @JoinColumn
    private LearningParty learningParty;

    @OneToMany
    private List<Course> courses;
}

