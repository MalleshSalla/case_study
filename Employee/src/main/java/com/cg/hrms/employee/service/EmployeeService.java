package com.cg.hrms.employee.service;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import com.cg.hrms.employee.exception.EmployeeNotFoundException;
import com.cg.hrms.employee.exception.NoProperDataException;
import com.cg.hrms.employee.model.Employee;

public interface EmployeeService {

public Employee addEmployee(@RequestBody Employee employee) throws NoProperDataException;



public Employee getEmployeeById(@PathVariable long id) throws EmployeeNotFoundException;


Employee deleteEmpById(long id) throws EmployeeNotFoundException;



List<Employee> getAllEmployees() throws EmployeeNotFoundException;





Employee updateEmployee(Long id, Employee employee) throws EmployeeNotFoundException;


















}
	

