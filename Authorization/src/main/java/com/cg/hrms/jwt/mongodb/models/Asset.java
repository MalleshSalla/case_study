package com.cg.hrms.jwt.mongodb.models;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data 
@Document(collection = "ass")
public class Asset {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long id;
	public static final String SEQUENCE_NAME="db_sequence_asset";
    
	private String assetName;
	private String assetModelNo;
	private String assetType;
	private Long employeeId;


}
