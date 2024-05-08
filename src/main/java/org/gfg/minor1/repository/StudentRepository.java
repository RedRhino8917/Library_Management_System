package org.gfg.minor1.repository;

import org.gfg.minor1.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Integer> {

    List<Student> findByPhoneNo(String phoneNo);
    Student findByEmail(String email);
}
