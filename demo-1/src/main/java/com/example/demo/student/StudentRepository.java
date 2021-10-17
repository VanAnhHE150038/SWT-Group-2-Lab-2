package com.example.demo.student;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
//When looking at this, you know this interface is responsible for data access
public interface StudentRepository extends JpaRepository<Student , Long>{ 
	//this is naming convention of anything that access to the DB (and his has to be an interface).
	//the 1st parameter : the type of object you want this repository to work with
	//the 2nd parameter : the type of id that we want (in this case id of student is Long)
	
	//SELECT * FROM student where email = ?
	Optional<Student> findStudentByEmail(String email){
		
	}
}
