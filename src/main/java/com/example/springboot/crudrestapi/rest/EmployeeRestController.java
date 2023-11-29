package com.example.springboot.crudrestapi.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.crudrestapi.entity.Employee;
import com.example.springboot.crudrestapi.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> displayAllEmployees() {
        return employeeService.findAllService();
    }

    @GetMapping("/employees/{id}")
    public Employee displaySingleEmployee(@PathVariable int id) {
        Employee emp = employeeService.findEmployeeByIdService(id);
        if (emp == null) {
            throw new RuntimeException("Employee not found with id = " + id);
        } else {
            return emp;
        }
    }

    @PostMapping("/employees")
    public Employee addNewEmployee(@RequestBody Employee employee) {
        employee.setId(0);
        Employee dbEmployee = employeeService.saveEmployeeService(employee);
        return dbEmployee;
    }

    @PutMapping("/employees")
    public Employee updateExistingEmployee(@RequestBody Employee employee) {
        Employee dbEmployee = employeeService.saveEmployeeService(employee);
        return dbEmployee;
    }

    @DeleteMapping("/employees/{id}")
    public String deleteSingleEmployee(@PathVariable int id) {
        Employee emp = employeeService.findEmployeeByIdService(id);
        if (emp == null) {
            throw new RuntimeException("Employee not found with id = " + id);
        } else {
            employeeService.deleteEmployeeByIdService(id);
            return "Employee deleted successfully with id = " + id;
        }

    }
}
