package com.example.ex4;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
    List<Student> findByAgeGreaterThanEqual(int x);

    long countByIeltsScore(double x);

    Iterable<Student> findByNameContainingIgnoreCase(String name);
}
