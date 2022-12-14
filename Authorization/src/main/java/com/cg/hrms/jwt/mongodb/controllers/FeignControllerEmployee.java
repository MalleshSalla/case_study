package com.cg.hrms.jwt.mongodb.controllers;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.hrms.jwt.mongodb.exception.EmployeeNotFoundException;
import com.cg.hrms.jwt.mongodb.exception.NoProperDataException;
import com.cg.hrms.jwt.mongodb.models.Employee;
import com.cg.hrms.jwt.mongodb.restclient.EmployeeClient;
import com.cg.hrms.jwt.mongodb.security.services.SequenceGeneratorEmployeeService;

import lombok.extern.slf4j.Slf4j;



@RestController
@Slf4j
@RequestMapping("/empl")
public class FeignControllerEmployee {
	
	@Autowired
	private EmployeeClient employeeClient;
	
	@Autowired
	private SequenceGeneratorEmployeeService service;
	
	@GetMapping("/emp/all")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<List<Employee>> getAllEmployees() throws EmployeeNotFoundException  {
		return employeeClient.getAllEmployees();
	}

	 //only user
	@PostMapping("/emp/create")  //this data should come
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Employee> addEmployee(@Valid @RequestBody Employee employee) throws NoProperDataException {
		employee.setId(service.getSequenceNumber(Employee.SEQUENCE_NAME));
		return employeeClient.addEmployee(employee);
	}
	
	@GetMapping("/emp/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) throws EmployeeNotFoundException{
		Employee employeeRetrieved = employeeClient.getEmployeeById(id);
		return new ResponseEntity<Employee>(employeeRetrieved, HttpStatus.OK);
		
	}

	@PutMapping("/emp/update/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id,@RequestBody  Employee employee) throws EmployeeNotFoundException {
		Employee employeeUpdated=employeeClient.updateEmployee(id,employee);
		log.debug("updated employee is {}"+employeeUpdated);
		  return new ResponseEntity<Employee> (employeeUpdated, HttpStatus.CREATED);
//		  return assetClient.updateAsset(asset);
	}

	  //delete  -> only user
	@DeleteMapping("/emp/delete/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Employee> deleteEmpById(@Valid @PathVariable long id) throws EmployeeNotFoundException {
		Employee employeeRemoved=employeeClient.deleteEmpById(id);
		  return new ResponseEntity<Employee> (employeeRemoved, HttpStatus.CREATED);
	}
}