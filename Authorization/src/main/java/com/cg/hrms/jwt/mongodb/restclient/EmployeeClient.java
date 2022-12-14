package com.cg.hrms.jwt.mongodb.restclient;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cg.hrms.jwt.mongodb.models.Employee;



@FeignClient(name = "employee-service" ,url = "http://localhost:3000/employee")
public interface EmployeeClient {
	
	@GetMapping("/emp/all")
	public ResponseEntity<List<Employee>> getAllEmployees();

	
	@PostMapping("/emp/create")  
	public ResponseEntity<Employee> addEmployee(Employee employee);


	@DeleteMapping("/emp/delete/{id}")
	public Employee deleteEmpById(@PathVariable ("id") long id);
	
	
	@PutMapping("/emp/update/{id}")
	public Employee updateEmployee(@PathVariable ("id") Long id,@RequestBody Employee employee);
	
	@GetMapping("/emp/{id}")
	public Employee getEmployeeById(@PathVariable ("id") Long id);

	
	
	
}