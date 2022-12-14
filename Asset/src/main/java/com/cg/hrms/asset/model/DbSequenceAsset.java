package com.cg.hrms.asset.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;




@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "db_sequence_asset")
public class DbSequenceAsset {

	  	@Id
	    private String  id;
	    private int seq;

}