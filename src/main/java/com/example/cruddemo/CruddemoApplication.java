package com.example.cruddemo;

import com.example.cruddemo.Entity.Student;
import com.example.cruddemo.dao.StudentDAO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO){
		return runner ->{
			//createStudent(studentDAO);
			//createMultipleStudents(studentDAO);
			//readStudent(studentDAO);
			//queryForStudents(studentDAO);
			//queryForStudentsByLastName(studentDAO);
			//updateStudent(studentDAO);
			//deleteStudent(studentDAO);
			deleteAllStudents(studentDAO);
		};
	}

	private void deleteAllStudents(StudentDAO studentDAO) {
		System.out.println("Deleting all the students");
		int numRowsDeleted = studentDAO.deleteAll();
		System.out.println(numRowsDeleted);
	}

	private void deleteStudent(StudentDAO studentDAO) {
		int studentId = 3;
		System.out.println("Deleting the student id: "+ studentId);
		studentDAO.delete(studentId);
	}

	private void updateStudent(StudentDAO studentDAO) {
		// retrieve student based on the id : PK
		int studentId = 1;
		System.out.println("getting student with id: "+ studentId);
		Student myStudent = studentDAO.findById(studentId);

		// change the first name to "Scooby"
		System.out.println("Updating student ...");
		myStudent.setFirstName("Scooby");

		// update the student
		studentDAO.update(myStudent);
		// display the updated student
		System.out.println("Updated student :" + myStudent);
	}

	private void queryForStudentsByLastName(StudentDAO studentDAO) {
		// get a list of students
		List<Student> theStudents = studentDAO.findByLastName("Duck");

		//display list of students
		for(Student tempStudent : theStudents){
			System.out.println(tempStudent);
		}

	}

	private void queryForStudents(StudentDAO studentDAO) {
		// get a list of students
		List<Student> theStudents = studentDAO.findAll();
		// display list of students
		for(Student tempStudent: theStudents){
			System.out.println(tempStudent);
		}
	}

	private void readStudent(StudentDAO studentDAO) {
		// create a student object
		System.out.println("creating new student object..");
		Student tempStudent = new Student("Daffy", "Duck", "daffy@gmail.com");
		// save the student
		System.out.println("Saving the student");
		studentDAO.save(tempStudent);
		// display id of the saved student
		int theId = tempStudent.getId();
		System.out.println("Generated id: " + theId);
		// retrieve student based on the id: pk
		System.out.println("Retrieving student with id: " + theId);
		Student myStudent = studentDAO.findById(theId);
		//display content
		System.out.println("Found the student: "+ myStudent);
	}

	private void createMultipleStudents(StudentDAO studentDAO) {
		System.out.println("Creating new student object");
		Student tempStudent1 = new Student("KC", "ch", "kc@gmail.com");
		Student tempStudent2 = new Student("KC1", "ch", "kc1@gmail.com");
		Student tempStudent3 = new Student("KC2", "ch", "kc2@gmail.com");
		System.out.println("saving the students");
		studentDAO.save(tempStudent1);
		studentDAO.save(tempStudent2);
		studentDAO.save(tempStudent3);
	}

	private void createStudent(StudentDAO studentDAO){
		// create the student obj
		System.out.println("Creating new student object");
		Student tempStudent = new Student("KC", "ch", "kc@gmail.com");

		// save the student obj
		System.out.println("Saving the student...");
		studentDAO.save(tempStudent);
		// display the id of the saved student
		System.out.println("Saved student. Generated id: "+ tempStudent.getId());
	}


}
