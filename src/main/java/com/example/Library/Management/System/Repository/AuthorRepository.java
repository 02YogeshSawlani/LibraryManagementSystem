package com.example.Library.Management.System.Repository;

import com.example.Library.Management.System.Entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author,Integer> {


}
