package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping; 
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Student;
import com.example.demo.service.StuService;

@RestController
@RequestMapping("/api")
public class StudentController {
    @Autowired
    private StuService stuService;
    
    @PostMapping("/user")
    public ResponseEntity<Student> createUser(@RequestBody Student student){
        Student createdUser = stuService.createUser(student);
        if (createdUser != null) {
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user")
    public ResponseEntity<List<Student>> getAllUsers(){
        List<Student> users = stuService.getAllUsers();
        if (!users.isEmpty()) {
            return new ResponseEntity<>(users, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Student> getUserById(@PathVariable int userId){
        Optional<Student> user = stuService.getUserById(userId);
        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/user/{userId}")
    public ResponseEntity<Student> updateUser(@PathVariable int userId, @RequestBody Student updatedStudent) {
        Optional<Student> optionalStudent = stuService.getUserById(userId);
        if (optionalStudent.isPresent()) {
            Student existingStudent = optionalStudent.get();
            existingStudent.setName(updatedStudent.getName());
            existingStudent.setEmail(updatedStudent.getEmail());
            existingStudent.setAge(updatedStudent.getAge());
            Student updatedUser = stuService.updateUser(existingStudent);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/user/{userId}") 
    public ResponseEntity<String> deleteUser(@PathVariable int userId) {
        boolean deleted = stuService.deleteUser(userId);
        if (deleted) {
            return new ResponseEntity<>("User with id " + userId + " has been deleted successfully.", HttpStatus.NO_CONTENT);
        }
            return new ResponseEntity<>("User with id " + userId + " not found.", HttpStatus.NOT_FOUND);
        }
    }