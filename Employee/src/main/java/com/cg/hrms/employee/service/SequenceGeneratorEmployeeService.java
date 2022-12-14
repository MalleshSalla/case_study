package com.cg.hrms.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.cg.hrms.employee.model.DbSequenceEmployee;
import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

import java.util.Objects;

@Service
public class SequenceGeneratorEmployeeService {

    @Autowired
    private MongoOperations mongoOperations;

     public long getSequenceNumber(String sequenceName) {
            //get sequence no
            Query query = new Query(Criteria.where("id").is(sequenceName));
            //update the sequence no
            Update update = new Update().inc("seq",1);
            //modify in document
            //login id will start from 500
            DbSequenceEmployee counter = mongoOperations
                    .findAndModify(query,
                            update, options().returnNew(true).upsert(true),
                            DbSequenceEmployee.class);

            return !Objects.isNull(counter) ? counter.getSeq() :1;
        }
 
}