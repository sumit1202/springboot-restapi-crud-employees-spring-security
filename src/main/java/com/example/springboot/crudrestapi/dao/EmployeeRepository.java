package com.example.springboot.crudrestapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboot.crudrestapi.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    
}
