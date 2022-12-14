package com.cg.hrms.asset.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DbSequenceAssetTest {
	
	
	DbSequenceAsset d1;
	@BeforeEach
	public void before() {
		d1=new DbSequenceAsset("1",1);
	}
	
	@AfterEach
	public void after() {
		d1=null;
	}
	@Test
	void testGetId() {
		assertEquals("1", d1.getId());
	}

	@Test
	void testGetSeq() {
		assertEquals(1, d1.getSeq());
	}
}