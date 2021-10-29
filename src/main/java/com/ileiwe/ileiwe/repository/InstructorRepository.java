package com.ileiwe.ileiwe.repository;

import com.ileiwe.ileiwe.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorRepository extends  JpaRepository<Instructor, Long>{
}
