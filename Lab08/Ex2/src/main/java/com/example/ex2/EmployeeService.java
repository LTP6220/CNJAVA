package com.example.ex2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service

public class EmployeeService {

    @Autowired
    private EmployeeRepository empRepo;

    public List<Employee> getAllEmployees() {
        return empRepo.findAll();
    }

    public Employee addEmployee(Employee emp) {
        return empRepo.save(emp);
    }

    public Optional<Employee> getEmployeeById(int id) {
        return empRepo.findById(id);
    }

    public Employee updateEmployee(int id, Employee emp) {
        Optional<Employee> empToUpdate = empRepo.findById(id);

        if (empToUpdate.isPresent()) {
            emp.setId(id);
            return empRepo.save(emp);
        } else {
            return null;
        }
    }

    public void deleteEmployee(int id) {
        Optional<Employee> empToDelete = empRepo.findById(id);

        if (empToDelete.isPresent()) {
            empRepo.deleteById(id);
        } else {
            return;
        }
    }
}
