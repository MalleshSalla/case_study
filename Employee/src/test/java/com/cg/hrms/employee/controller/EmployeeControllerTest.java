package com.cg.hrms.employee.controller;
import static org.hamcrest.Matchers.hasSize;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.cg.hrms.employee.model.Employee;
import com.cg.hrms.employee.service.EmployeeServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;


@SpringBootTest
@AutoConfigureMockMvc

public class EmployeeControllerTest {
	
		@MockBean
		private EmployeeServiceImpl service;
		
		@Autowired
		MockMvc mockMvc;
		
		
		@Test
		void testServiceNotNull() {
			assertThat(service).isNotNull();
		}
		
		@Test
		void testMockMvcNotNull() {
			assertThat(mockMvc).isNotNull();
		}
		@Test
		void testAdd() throws Exception {
			Employee e1=new Employee((long) 1,"Prashant","HR","Analyst",(long)10001);
			String s="added successfully....";
			when(service.addEmployee(e1)).thenReturn(e1);
			
			ObjectMapper mapper=new ObjectMapper();
			mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
			ObjectWriter  writer=mapper.writer().withDefaultPrettyPrinter();
			String reqstr=writer.writeValueAsString(e1);
		mockMvc.perform(post("/employee/emp/create")
				.contentType("application/json")
				.content(reqstr))
		.andExpect(status().isOk())
		.andExpect(content().string(s));
			
		}
		@Test
		void testGetAllAssets() throws Exception {
			Employee e1=new Employee((long) 1,"Prashant","HR","Analyst",(long)10001);
			Employee e2=new Employee((long) 2,"Prash","HR","Support",(long)10002);
			List<Employee> employeeList=new ArrayList<Employee>();
			employeeList.add(e1);
			employeeList.add(e2);
			when(service.getAllEmployees()).thenReturn(employeeList);
		mockMvc.perform(get("/employees/emp/all/"))
		.andExpect(status().isOk())
		.andExpect(content().contentType("application/json"))
		.andExpect(jsonPath("$[*]", hasSize(2)))
		.andExpect(jsonPath("$[0].id").value(1))
		.andExpect(jsonPath("$[0].AssetName").value("Apple"))
		.andExpect(jsonPath("$[0].AssetModelNo").value("LGV4"))
		.andExpect(jsonPath("$[0].AssetType").value("Laptop"));
			
		}
		
		@Test
		void testDeleteAssetById() throws Exception {
			Employee e1=new Employee((long) 1,"Prashant","HR","Analyst",(long)10001);
			String s="deleted successfully....";
		when(service.deleteEmpById(1)).thenReturn(e1);
		mockMvc.perform(delete("/employee/emp/delete/1"))
		.andExpect(status().isOk())
		.andExpect(content().string(s));	
		}
		
		@Test
		void testdeleteAssetInvalidId() throws Exception {
			Employee e1=new Employee((long) 1,"Prashant","HR","Analyst",(long)10001);
			@SuppressWarnings("unused")
			String s="deleted successfully....";
			when(service.deleteEmpById(11)).thenReturn(e1);
		MvcResult result=mockMvc.perform(delete("/employee/delete/3"))
		.andExpect(status().isOk())
		.andReturn();
		assertThat(result.getResponse().toString())
		.as("Employee with the id 1 doesn't exist");
			
		}
}

