package com.example.ex3;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class Ex3Application implements CommandLineRunner {
    private final StudentRepository studentRepository;


    public Ex3Application(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Ex3Application.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        // Add at least 3 students to the database
        Student student1 = new Student(null, "John", 25, "john@example.com", 7);
        Student student2 = new Student(null, "Alice", 23, "alice@example.com", 8);
        Student student3 = new Student(null, "Bob", 22, "bob@example.com", 6);

        studentRepository.saveAll(List.of(student1, student2, student3));

        // Read the student list and print it to the console
        Iterable<Student> students = studentRepository.findAll();
        students.forEach(System.out::println);

        // Update any student's information and print out the results after updating
        Student studentToUpdate = studentRepository.findById(1L).orElse(null);
        if (studentToUpdate != null) {
            studentToUpdate.setAge(26);
            studentRepository.save(studentToUpdate);
            System.out.println("Updated student: " + studentToUpdate);
        }

        // Delete a student from the database and print the result after deleting
        studentRepository.deleteById(2L);
        System.out.println("Deleted student with ID 2");

        // Read the updated list of students
        students = studentRepository.findAll();
        students.forEach(System.out::println);
    }
}