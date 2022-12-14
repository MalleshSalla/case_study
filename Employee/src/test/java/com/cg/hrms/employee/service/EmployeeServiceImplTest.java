package com.cg.hrms.employee.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.hrms.employee.exception.EmployeeNotFoundException;
import com.cg.hrms.employee.exception.NoProperDataException;
import com.cg.hrms.employee.model.Employee;
import com.cg.hrms.employee.repository.EmployeeRepository;

@SpringBootTest
@AutoConfigureMockMvc

public class EmployeeServiceImplTest {
	
	@Mock
	private EmployeeRepository employeerepository;
	
	@InjectMocks
	private EmployeeServiceImpl employeeServiceImpl;
	
	@Test
	void employeeerviceNotNullTest() {
		assertThat(employeeServiceImpl).isNotNull();

	
	}
	@SuppressWarnings({ "unchecked", "unlikely-arg-type" })
	@Test
	void testAddEmployees() throws EmployeeNotFoundException, NoProperDataException {
		Employee e1=new Employee((long) 1,"Prashant","HR","Analyst",(long)10001);
		((List<Employee>) assertThat(((EmployeeServiceImpl) employeerepository).addEmployee(e1)))
		.contains("added successfully....");
	
	}	

	
	@Test
	void getAllAssetsTest() throws EmployeeNotFoundException 
	{
		Employee e1=new Employee((long) 1,"Prashant","HR","Analyst",(long)10001);
		Employee e2=new Employee((long) 2,"Prash","HR","Support",(long)10002);
		List<Employee> employeeList=new ArrayList<Employee>();
		employeeList.add(e1);
		employeeList.add(e2);
		when(employeerepository.findAll()).thenReturn(employeeList);
		assertEquals(employeeList,employeeServiceImpl.getAllEmployees());
	}
	
	@SuppressWarnings({ "unchecked", "unlikely-arg-type" })
	@Test
	void testAddEmployeeAlreadyExists() throws EmployeeNotFoundException {
		Employee e1=new Employee((long) 1,"Prashant","HR","Analyst",(long)10001);
		when(employeerepository.findById(101)).thenReturn(Optional.of(e1));
	try {
		((List<Employee>) assertThat(employeeServiceImpl.addEmployee(e1)))
		.contains("Employee with the id "+e1.getId()+" already exist");
	}catch(Exception e) {
		
	}
	}
	@Test
	void getEmployeeByIdTest() throws EmployeeNotFoundException {
		Employee e1=new Employee((long) 1,"Prashant","HR","Analyst",(long)10001);
		when(employeerepository.findById(101)).thenReturn(Optional.of(e1));
	assertEquals(e1,employeeServiceImpl.getEmployeeById((long) 1));
	}
	
	@Test
	void testgetEmployeeByInvalidId() throws EmployeeNotFoundException {
		Employee e1=new Employee((long) 1,"Prashant","HR","Analyst",(long)10001);
		when(employeerepository.findById(101)).thenReturn(Optional.of(e1));
		try {
			assertThat(employeeServiceImpl.getEmployeeById((long) 1)).as("Employee with the id 1 doesn't exist");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testupdateEmployee() throws EmployeeNotFoundException {
		Employee e1=new Employee((long) 1,"Prashant","HR","Analyst",(long)10001);
		when(employeerepository.findById(101)).thenReturn(Optional.of(e1));
		assertThat(employeeServiceImpl.updateEmployee((long) 1));
		
		
		try {
			assertThat(employeeServiceImpl.getEmployeeById((long) 1)).as("Updated");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "unchecked", "unlikely-arg-type" })
	@Test
	void testDeleteEmployeeByInvalidId() throws EmployeeNotFoundException {
		Employee e1=new Employee((long) 1,"Prashant","HR","Analyst",(long)10001);
		when(employeerepository.findById(101)).thenReturn(Optional.of(e1));
		try {
			((Collection<Employee>) assertThat(employeeServiceImpl.deleteEmpById(1)))
			.contains("Employee with the id "+e1.getId()+" doesn't exist");
		}catch(Exception e) {
			
		}
	}
	
	
	@SuppressWarnings({ "unchecked", "unlikely-arg-type" })
	@Test
	void testDeleteEmployeeById() throws EmployeeNotFoundException {
		Employee e1=new Employee((long) 1,"Prashant","HR","Analyst",(long)10001);
		when(employeerepository.findById(101)).thenReturn(Optional.of(e1));
		
		((List<Employee>) assertThat(employeeServiceImpl.deleteEmpById(101)))
		.contains("deleted successfully....");
	}
}

