package com.mongo.example.mongodbexample.service;

import com.mongo.example.mongodbexample.entity.DbSequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import javax.management.Query;

import java.util.Objects;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
public class SequenceGenerator {
    @Autowired
    private MongoOperations mongoOperations;

    public int getSequenceNumber(String sequenceName){
        DbSequence counter = mongoOperations.findAndModify(query(where("_id").is(sequenceName)),
                new Update().inc("seq",1), options().returnNew(true).upsert(true),
                DbSequence.class);
        return !Objects.isNull(counter) ? counter.getSeq() : 1;
    }

}
