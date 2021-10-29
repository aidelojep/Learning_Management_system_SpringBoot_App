package com.ileiwe.ileiwe.repository;

import com.ileiwe.ileiwe.model.LearningParty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

public interface LearningPartyRepository extends JpaRepository<LearningParty, Long> {

    //You can pass SQL query to using @Query annotations when you want to customize your query
//   @Query("select '*' from LearningParty" + " as L where L.email=:email")
//    LearningParty findUserByEmail(String email);

    //Performs a query to your database to find user emails
    LearningParty findByEmail(String email);

}
