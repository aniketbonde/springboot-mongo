package com.mongo.example.mongodbexample.controller;

import com.mongo.example.mongodbexample.entity.Student;
import com.mongo.example.mongodbexample.repo.StudentRepository;
import com.mongo.example.mongodbexample.service.SequenceGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private SequenceGenerator sequenceGenerator;
    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/")
    public ResponseEntity<List<Student>> getStudent(){
        return ResponseEntity.ok(this.studentRepository.findAll());
    }

    @PostMapping("/")
    public ResponseEntity<Student> addStudent(@RequestBody Student student){
        student.setId(sequenceGenerator.getSequenceNumber(Student.SEQUENCE_NAME));
        Student save = studentRepository.save(student);
        return ResponseEntity.ok(save);
    }

}
