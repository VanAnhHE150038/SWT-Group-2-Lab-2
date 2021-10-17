package com.example.demo.student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration//this is a configuration file
public class StudentConfig {
	
	@Bean
	CommandLineRunner commandLineRunner(StudentRepository repository) {
		return args ->{
			Student khai = new Student("Khai", "k@gmail.com", LocalDate.of(2000, Month.JANUARY, 5));
			Student trinh = new Student("Trinh", "t@gmail.com", LocalDate.of(2004, Month.JANUARY, 5));
			repository.saveAll(List.of(khai,trinh));
		};
	}
}
