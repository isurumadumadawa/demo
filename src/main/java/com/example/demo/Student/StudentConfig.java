package com.example.demo.Student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {

	@Bean
	CommandLineRunner commandLineRunner(StudentRepository repository) {
	return args->{
	Student Isuru =	new Student("Isuru","isuru@gmail.com",LocalDate.of(1998, Month.AUGUST, 4)) ;
	Student Ishan =	new Student("Ishan","ishan@gmail.com",LocalDate.of(2000, Month.APRIL, 16)) ;
	
	repository.saveAll(List.of(Isuru,Ishan));
	
	};	
	}
	
}



