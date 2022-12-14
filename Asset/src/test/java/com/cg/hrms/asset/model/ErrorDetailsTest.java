package com.cg.hrms.asset.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ErrorDetailsTest {

	Asset a1;
	@BeforeEach
	public void before() {
		a1=new Asset((long) 1,"Apple","LGV4","Laptop",(long)1);
	}
	
	@AfterEach
	public void after() {
		a1=null;
	}

	@Test
	void testGetId() {
		assertEquals(1, a1.getId());
	}

	@Test
	void testGetAssetName() {
		assertEquals("Apple", a1.getAssetName());
	}

	@Test
	void testGetAssetModelNo() {
		assertEquals("LGV4", a1.getAssetModelNo());
	}

	@Test
	void testGetAssetType() {
		assertEquals("Laptop", a1.getAssetType());
	}

	@Test
	void testGetEmployeeId() {
		assertEquals(1, a1.getEmployeeId());
	}
}
