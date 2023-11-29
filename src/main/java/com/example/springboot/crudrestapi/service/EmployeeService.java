package com.example.springboot.crudrestapi.service;

import java.util.List;
import com.example.springboot.crudrestapi.entity.Employee;

public interface EmployeeService {

    List<Employee> findAllService();

    Employee findEmployeeByIdService(int id);

    Employee saveEmployeeService(Employee employee);

    void deleteEmployeeByIdService(int id);

}
