package com.example.ex6;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface StudentPagingAndSortingRepository extends PagingAndSortingRepository<Student, Long> {
}
