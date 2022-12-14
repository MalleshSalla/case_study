package com.cg.hrms.employee.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cg.hrms.employee.model.Employee;

@Repository
public interface EmployeeRepository extends MongoRepository< Employee, String>{

	void deleteById(Long id);

	Object findAllById(Long id);

	Optional<Employee> findById(long id);

	Optional<Employee> deleteEmpById(long id);



	

	

}
