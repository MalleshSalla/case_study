package com.cg.hrms.jwt.mongodb.models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data 
@Document(collection = "employee")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
    private Long id;
	public static final String SEQUENCE_NAME="db_sequence";
    
	@Size(min=3,max=12,message ="Name lenght must be min 3 and max 12")
	private String employeeName;
	@Size(min=3,max=30,message = "Department length must be min 5 and max 30")
	private String employeeDepartment;
	@Size(min=3,max=30,message = "Designation length must be min 5 and max 30")
	private String employeeDesignation;

	@Min(value = 5000,message = "Salary minimum is 5000")
	@Max(value =  1000000,message = "salary maximum is 100000")
	private long  employeeSalary;
}
