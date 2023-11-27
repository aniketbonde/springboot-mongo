package com.mongo.example.mongodbexample.repo;

import com.mongo.example.mongodbexample.entity.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<Student,Integer> {

}
