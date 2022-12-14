package com.cg.hrms.asset.controller;
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

import com.cg.hrms.asset.model.Asset;
import com.cg.hrms.asset.service.AssetServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;


@SpringBootTest
@AutoConfigureMockMvc

public class AssetControllerTest {
	
		@MockBean
		private AssetServiceImpl service;
		
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
		void testAddAsset() throws Exception {
			Asset a1=new Asset((long) 1,"Apple","LGV4","Laptop",(long)1);
			String s="added successfully....";
			when(service.addAsset(a1)).thenReturn(a1);
			
			ObjectMapper mapper=new ObjectMapper();
			mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
			ObjectWriter  writer=mapper.writer().withDefaultPrettyPrinter();
			String reqstr=writer.writeValueAsString(a1);
		mockMvc.perform(post("/assets/create")
				.contentType("application/json")
				.content(reqstr))
		.andExpect(status().isOk())
		.andExpect(content().string(s));
			
		}
		@Test
		void testGetAllAssets() throws Exception {
			Asset a1=new Asset((long) 1,"Apple","LGV4","Laptop",(long)1);
			Asset a2=new Asset((long) 2,"Microsoft","MS12","Laptop",(long)2);
			List<Asset> assetList=new ArrayList<Asset>();
			assetList.add(a1);
			assetList.add(a2);
			when(service.getAllAssets()).thenReturn(assetList);
		mockMvc.perform(get("/assets/get/all/"))
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
			Asset a1=new Asset((long) 1,"Apple","LGV4","Laptop",(long)1);
			String s="deleted successfully....";
		when(service.deleteAssetById(1)).thenReturn(a1);
		mockMvc.perform(delete("/assets/delete/1"))
		.andExpect(status().isOk())
		.andExpect(content().string(s));	
		}
		
		@Test
		void testdeleteAssetInvalidId() throws Exception {
			Asset a1=new Asset((long) 1,"Apple","LGV4","Laptop",(long)1);
			@SuppressWarnings("unused")
			String s="deleted successfully....";
			when(service.deleteAssetById(101)).thenReturn(a1);
		MvcResult result=mockMvc.perform(delete("/assets/delete/1"))
		.andExpect(status().isOk())
		.andReturn();
		assertThat(result.getResponse().toString())
		.as("Asset with the id 1 doesn't exist");
			
		}
}

