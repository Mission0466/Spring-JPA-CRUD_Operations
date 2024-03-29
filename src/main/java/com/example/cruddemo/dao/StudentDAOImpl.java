package com.example.cruddemo.dao;

import com.example.cruddemo.Entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO{

    // define fields for entity manager.
    private EntityManager entityManager;

    // inject EntityManager using constructor
    @Autowired
    public StudentDAOImpl(EntityManager theEntityManager){
        entityManager = theEntityManager;
    }


    // implement save method
    @Override
    @Transactional
    public void save(Student theStudent) {
        entityManager.persist(theStudent);   // saves the student to the DB
    }

    @Override
    public Student findById(Integer id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> findAll() {
        // create query
        TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student order by lastName", Student.class);   // always remember these are files that present in our model class not from DB
        // return the query results
        return theQuery.getResultList();

    }

    @Override
    public List<Student> findByLastName(String lastName) {
        // create query
        TypedQuery<Student> theQuery = entityManager.createQuery("from Student where lastName=:theDate", Student.class);
        // set query parameters
        theQuery.setParameter("theDate", lastName);

        // return query results
        return theQuery.getResultList();
    }

    @Override
    @Transactional
    public void update(Student theStudent) {
        entityManager.merge(theStudent);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        // retrieve the student first
        Student theStudent = entityManager.find(Student.class, id);
        // delete the student
        entityManager.remove(theStudent);
    }

    @Override
    @Transactional
    public int deleteAll() {
        int numRowsDeleted = entityManager.createQuery("Delete from Student").executeUpdate();
        return numRowsDeleted;
    }
}
