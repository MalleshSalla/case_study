package com.cg.hrms.jwt.mongodb.models;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "db_sequence_asset")
public class DbSequenceAsset {

	  	@Id
	    private String  id;
	    private int seq;

}