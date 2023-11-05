package com.example.ex6;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootApplication
public class Ex6Application {

    public static void main(String[] args) {
        SpringApplication.run(Ex6Application.class, args);
    }

    @Bean
    public CommandLineRunner demo(StudentPagingAndSortingRepository repository) {
        return (args) -> {
            // Sort by age in descending order, and then by IELTS score in ascending order
            Sort ageDescIELTSScoreAsc = Sort.by(Sort.Order.desc("age"), Sort.Order.asc("ieltsScore"));

            // Create a pageable object for paging
            PageRequest pageable = PageRequest.of(0, 10, ageDescIELTSScoreAsc);

            // Read students with sorting and paging
            Page<Student> studentsPage = repository.findAll(pageable);

            // Print the sorted and paged students
            studentsPage.forEach(student -> {
                System.out.println("Name: " + student.getName() + ", Age: " + student.getAge() + ", IELTS Score: " + student.getIeltsScore());
            });

            // Assuming there are more than 10 students in the list
            int startPage = 0;
            int pageSize = 10;

            Pageable page = PageRequest.of(startPage, pageSize, ageDescIELTSScoreAsc);

            Page<Student> studentsPage2 = repository.findAll(page);

            // Get and print students 4, 5, and 6 from the sorted and paged list
            List<Student> students456 = studentsPage2.getContent().subList(3, 6);

            students456.forEach(student -> {
                System.out.println("Name: " + student.getName() + ", Age: " + student.getAge() + ", IELTS Score: " + student.getIeltsScore());
            });
        };
    }
}
