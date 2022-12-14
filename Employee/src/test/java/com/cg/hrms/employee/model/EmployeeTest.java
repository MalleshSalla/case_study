package com.cg.hrms.employee.model;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class EmployeeTest {


	Employee e1;
	@BeforeEach
	public void before() {
		e1=new Employee((long) 1,"Prashant","HR","Analyst",(long)10001);
	}
	
	@AfterEach
	public void after() {
		e1=null;
	}

	@Test
	void testGetId() {
		assertEquals(1, e1.getId());
	}

	@Test
	void testGetemployeeName() {
		assertEquals("Prashant", e1.getEmployeeName());
	}

	@Test
	void testGetemployeeDepartment() {
		assertEquals("HR", e1.getEmployeeDepartment());
	}

	@Test
	void testGetemployeeDesignation() {
		assertEquals("Analyst", e1.getEmployeeDesignation());
	}

	@Test
	void testGetemployeeSalary() {
		assertEquals(10001, e1.getEmployeeSalary());
	}
	
}
