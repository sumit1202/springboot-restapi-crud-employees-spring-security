package com.example.springboot.crudrestapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.springboot.crudrestapi.dao.EmployeeRepository;
import com.example.springboot.crudrestapi.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepos) {
        this.employeeRepository = employeeRepos;
    }

    @Override
    public List<Employee> findAllService() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findEmployeeByIdService(int id) {
        Optional<Employee> result = employeeRepository.findById(id);
        Employee emp = null;
        if (result.isPresent()) {
            emp = result.get();
            return emp;
        } else {
            throw new RuntimeException("Couldn't find employee with id " + id);
        }
    }

    @Override
    public Employee saveEmployeeService(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployeeByIdService(int id) {
        employeeRepository.deleteById(id);
    }

}
