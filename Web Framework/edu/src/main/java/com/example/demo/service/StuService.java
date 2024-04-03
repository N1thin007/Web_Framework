package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Student;
import com.example.demo.repository.StuRepo;

@Service
public class StuService {
    @Autowired
    private StuRepo stuRepo;

    public Student createUser(Student student){
        return stuRepo.save(student);
    }

    public List<Student> getAllUsers(){
        return stuRepo.findAll();
    }

    public Optional<Student> getUserById(Integer userId){
        return stuRepo.findById(userId);
    }

    public Student updateUser(Student student){
        return stuRepo.save(student);
    }
    public boolean deleteUser(Integer userId) {
        Optional<Student> user = stuRepo.findById(userId);
        if (user.isPresent()) {
            stuRepo.deleteById(userId);
            return true;
        } else {
            return false;
        }
    }    
    
}
