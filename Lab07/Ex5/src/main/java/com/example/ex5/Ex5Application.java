package com.example.ex5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Ex5Application {
    @Autowired
    private StudentService studentServices;

    public static void main(String[] args) {
        SpringApplication.run(Ex5Application.class, args);
    }

    public void run(String... args) throws Exception {

        // Add at least 3 students to the database
        Student student1 = new Student(null, "John", 25, "john@example.com", 7);
        Student student2 = new Student(null, "Alice", 23, "alice@example.com", 8);
        Student student3 = new Student(null, "phu", 19, "bob@example.com", 6);

        studentServices.createStudent(student1);
        studentServices.createStudent(student2);
        studentServices.createStudent(student3);

        // Read the student list and print it to the console
        Iterable<Student> students = studentServices.getAllStudents();
        students.forEach(System.out::println);
//

        Iterable<Student> st = studentServices.findByAgeGreaterThanEqualCustom(20);
        for (Student s : st) {
            System.out.println(s);
        }

        System.out.println(studentServices.countByIeltsScoreCustom(8));

        System.out.println(studentServices.findByNameContainingIgnoreCaseCustom("phu"));
    }
}
