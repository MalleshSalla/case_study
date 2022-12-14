package com.cg.hrms.employee.controller;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.hrms.employee.exception.EmployeeNotFoundException;
import com.cg.hrms.employee.exception.NoProperDataException;
import com.cg.hrms.employee.model.Employee;
import com.cg.hrms.employee.service.EmployeeServiceImpl;
import com.cg.hrms.employee.service.SequenceGeneratorEmployeeService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/employee")
@CrossOrigin(origins = "http://localhost:4200/")
public class EmployeeController {
	

	@Autowired
	private SequenceGeneratorEmployeeService service;


	@Autowired
	private EmployeeServiceImpl employeeServiceImpl;
	
	@PostMapping("/emp/create")
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee)throws NoProperDataException{
		log.info("start");
		employee.setId(service.getSequenceNumber(Employee.SEQUENCE_NAME));
		Employee updatedEmployee =employeeServiceImpl.addEmployee(employee);
		log.debug("updated employee is {}"+updatedEmployee);
		 return new ResponseEntity<>(updatedEmployee,HttpStatus.CREATED);
		
	}
	


	@GetMapping("/emp/all")
	public ResponseEntity<List<Employee>> getAllEmployees() throws EmployeeNotFoundException{
		
		log.info("starting of get mapping");
		List<Employee> listOfAllEmployees = employeeServiceImpl.getAllEmployees();
		log.debug("listOfAllEmployees {}"+listOfAllEmployees);
		return new ResponseEntity<List<Employee>>(listOfAllEmployees, HttpStatus.OK);
		
		
	}


	@GetMapping("/emp/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) throws EmployeeNotFoundException{
		log.info("Getting Employee by id");
		Employee employeeRetrieved = employeeServiceImpl.getEmployeeById(id);
		log.debug("employeeRetrieved is {}"+employeeRetrieved);
		return new ResponseEntity<Employee>(employeeRetrieved, HttpStatus.OK);
		
	}
	
	
	@PutMapping("emp/update/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable long id,@RequestBody Employee employee) throws EmployeeNotFoundException, NoProperDataException{
		
		Employee employeeSaved = employeeServiceImpl.updateEmployee(id,employee);
		log.debug("employeeSaved is {}"+employeeSaved);
		return new ResponseEntity<Employee>(employeeSaved, HttpStatus.CREATED);
	
	}
	
	
	@DeleteMapping("emp/delete/{id}")
	public ResponseEntity<Employee> deleteEmpById(@PathVariable Long id) throws EmployeeNotFoundException{
		
		Employee remove=employeeServiceImpl.deleteEmpById(id);
		log.debug("employeeRemoved {}"+remove);
		return new ResponseEntity<Employee>(remove,HttpStatus.ACCEPTED);
		
	}

}

