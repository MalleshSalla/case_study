package com.cg.hrms.employee.service;
import java.util.ArrayList;
import java.util.List;
import java.util.function.IntPredicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.cg.hrms.employee.exception.EmployeeNotFoundException;
import com.cg.hrms.employee.exception.NoProperDataException;
import com.cg.hrms.employee.model.Employee;
import com.cg.hrms.employee.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
//	
//	@Autowired
//	private AuthFeign authFeign;
	
	String message = "Invalid Credentials";

	@Override
	public Employee addEmployee(@RequestBody Employee employee) throws NoProperDataException {
		
		log.info("start");
		if(employee!=null) 
		{
			employeeRepository.save(employee);
			log.debug("employee added");
		}
		else
		{
			throw new NoProperDataException("Please fill fields");
		}
		return employee;
		
	}


	

	@Override
	public  List<Employee> getAllEmployees() throws EmployeeNotFoundException {

		List<Employee> employees =new ArrayList<>();
		employees =employeeRepository.findAll();
		if(employees.size()==0){
			throw new EmployeeNotFoundException("Employee is empty");
		}
		else{
			log.info("get all Employees from the list");
			return (employeeRepository.findAll());
		}
}

	@Override
	public Employee getEmployeeById(long id) throws EmployeeNotFoundException{
		Employee employees=employeeRepository.findById(id).orElseThrow(()-> new  EmployeeNotFoundException("employee Not Found"+id));
		log.debug("getting employee by id {}"+employees);
		return employees;
	}



	@Override
	public Employee deleteEmpById(long id) throws EmployeeNotFoundException{
		Employee employees=employeeRepository.deleteEmpById(id).orElseThrow(()-> new  EmployeeNotFoundException("employee Not Found"+id));
		
		return employees;
	}
	

	
	


	@Override
	public Employee updateEmployee(Long id,Employee employee) throws EmployeeNotFoundException {
		
        Employee employees=employeeRepository.findById(id).orElseThrow(()-> new EmployeeNotFoundException("employee not "+id));
		
		employees.setEmployeeName(employee.getEmployeeName());
		employees.setEmployeeDepartment(employee.getEmployeeDepartment());
		employees.setEmployeeDesignation(employee.getEmployeeDesignation());
		employees.setEmployeeSalary(employee.getEmployeeSalary());
		
		
		final Employee updatedEmployee =employeeRepository.save(employee);
		log.debug("updatedEmployee {}"+updatedEmployee);
		return updatedEmployee;
		
	
	
	}




	public IntPredicate updateEmployee(long l) {
		// TODO Auto-generated method stub
		return null;
	}





	}