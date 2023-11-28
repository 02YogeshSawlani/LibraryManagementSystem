package com.example.Library.Management.System.Repository;

import com.example.Library.Management.System.Entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
    // In case you want to write your own queries that can be written here
}
