package com.example.cruddemo.dao;

import com.example.cruddemo.Entity.Student;

import java.util.List;

public interface StudentDAO {
    public void save(Student theStudent);

    Student findById(Integer id);

    List<Student> findAll();

    List<Student> findByLastName(String lastName);

    void update(Student theStudent);

    void delete(Integer id);

    //delete all the students
    int deleteAll();
}
