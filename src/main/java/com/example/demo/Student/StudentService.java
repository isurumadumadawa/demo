package com.example.demo.Student;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentService {

	private final StudentRepository studentRepository ; 
	
	
   @Autowired
	public StudentService(StudentRepository studentRepository) {
	
		this.studentRepository = studentRepository;
	}




	public List<Student> getStudents() {
		return studentRepository.findAll();
	}




	public void addNewStudents(Student student) {
		
	Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());
	
	if(studentByEmail.isPresent()) {
		throw new IllegalStateException("emial is already taken");
	}
	
	studentRepository.save(student);
	
		
	}




	public void deleteStudent(Long studentId) {
		
		boolean exist = studentRepository.existsById(studentId);
		
		if(!exist) {
			throw new IllegalStateException("Can't find Student");
		}
		
		studentRepository.deleteById(studentId);
		
	}



    @Transactional
	public void updateStudent(Long studentId, String name, String email) {
		
		Student student = studentRepository.findById(studentId)
				.orElseThrow(()-> new IllegalStateException("Can't find Student with student id is equal to "+studentId+""));
		
		
		if(name != null && name.length()>0 && !Objects.equals(name, student.getName())) {
			student.setName(name);
		}
		
		if(email != null && email.length()>0 && !Objects.equals(email, student.getEmail())) {
			
			Optional<Student> studentByEmail = studentRepository.findStudentByEmail(email);
			
			if(studentByEmail.isPresent()) {
				throw new IllegalStateException("emial is already taken");
			}
			
			student.setEmail(email);
		}
	}
		
	
}
