package com.cg.hrms.jwt.mongodb.security.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.cg.hrms.jwt.mongodb.models.DbSequenceAsset;

import java.util.Objects;
import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

@Service
public class SequenceGeneratorAssetService {
	

    @Autowired
    private MongoOperations mongoOperations;

	 public long getSequenceNumberForAsset(String sequenceName) {
         //get sequence no
         Query query = new Query(Criteria.where("id").is(sequenceName));
         //update the sequence no
         Update update = new Update().inc("seq",1);
         //modify in document
         //login id will start from 500
         DbSequenceAsset counter = mongoOperations
                 .findAndModify(query,
                         update, options().returnNew(true).upsert(true),
                         DbSequenceAsset.class);

         return !Objects.isNull(counter) ? counter.getSeq() :1;
     }

	
}