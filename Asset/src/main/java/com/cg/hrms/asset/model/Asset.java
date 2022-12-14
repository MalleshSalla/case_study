package com.cg.hrms.asset.model;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Document(collection = "ass")
public class Asset {
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long id;
	public static final String SEQUENCE_NAME="db_sequence_asset"; 
	private String assetName;
	private String assetModelNo;
	private String assetType;
	private Long   employeeId;


}
