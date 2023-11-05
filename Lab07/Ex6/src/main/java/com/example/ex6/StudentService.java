package com.example.ex6;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    public Iterable<Student> findStudentsByAgeGreaterThanEqual(int age) {
        return studentRepo.findByAgeGreaterThanEqual(age);
    }

    public long countStudentsByIeltsScore(double ieltsScore) {
        return studentRepo.countByIeltsScore(ieltsScore);
    }

    public Iterable<Student> findStudentsByNameContainingIgnoreCase(String name) {
        return studentRepo.findByNameContainingIgnoreCase(name);
    }

}
