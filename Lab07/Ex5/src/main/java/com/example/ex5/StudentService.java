package com.example.ex5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentService {
    @Autowired
    private StudentRepository studentRepo;

    public Student createStudent(Student student) {
        return studentRepo.save(student);
    }

    public Iterable<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    public Student getStudentById(Long id) {
        return studentRepo.findById(id).orElse(null);
    }

    public Student updateStudent(Student updatedStudent) {
        return studentRepo.save(updatedStudent);
    }

    public void deleteStudentById(Long id) {
        studentRepo.deleteById(id);
    }


    List<Student> findByAgeGreaterThanEqualCustom(@Param("age") int age) {
        return studentRepo.findByAgeGreaterThanEqualCustom(age);
    }

    long countByIeltsScoreCustom(@Param("ieltsScore") double ieltsScore) {
        return studentRepo.countByIeltsScoreCustom(ieltsScore);
    }

    List<Student> findByNameContainingIgnoreCaseCustom(@Param("name") String name) {
        return studentRepo.findByNameContainingIgnoreCaseCustom(name);
    }


}
