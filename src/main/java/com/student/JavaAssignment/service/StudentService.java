package com.student.JavaAssignment.service;

import com.student.JavaAssignment.entity.Student;
import com.student.JavaAssignment.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {


    private final StudentRepository studentRepository;

    public Student saveStudent(Student student) {
        student.calculateAge();
        return studentRepository.save(student);
    }

    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public List<Student> getStudentsBetweenAges(int startAge, int endAge) {
        return studentRepository.findByAgeBetween(startAge, endAge);
    }

}

