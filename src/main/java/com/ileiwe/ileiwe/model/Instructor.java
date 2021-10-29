package com.ileiwe.ileiwe.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.engine.internal.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
@Builder()
@AllArgsConstructor
@NoArgsConstructor
public class Instructor{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @NotNull
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String firstName;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String specialization;
    @Column(length=1000)
    private String bio;


    @OneToOne(cascade = CascadeType.PERSIST)
    //this is created so that the Instructor table will have an extra column to hold the id of the learning party
    @JoinColumn(nullable = false)
    private LearningParty learningParty;

    @OneToMany
    private List<Course> courses;
}

