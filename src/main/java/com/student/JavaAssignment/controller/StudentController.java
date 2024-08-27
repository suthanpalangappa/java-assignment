package com.student.JavaAssignment.controller;

import com.student.JavaAssignment.entity.Student;
import com.student.JavaAssignment.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/students/")
@RequiredArgsConstructor
public class StudentController {

    private final ModelMapper modelMapper;
    private final StudentService studentService;

    @PostMapping(value = "createStudent")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student savedStudent = studentService.saveStudent(student);
        return ResponseEntity.ok(savedStudent);
    }

    @GetMapping(value = "getStudentById/{studentId}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long studentId) {
        return studentService.getStudentById(studentId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "getAllStudents")
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }


    @PutMapping("update-age/{studentId}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long studentId, @RequestBody Student updatedStudent) {
        Optional<Student> existingStudent = studentService.getStudentById(studentId);
        if (existingStudent.isPresent()) {
            Student student = existingStudent.get();
            student.setName(updatedStudent.getName());
            student.setDobDay(updatedStudent.getDobDay());
            student.setDobMonth(updatedStudent.getDobMonth());
            student.setDobYear(updatedStudent.getDobYear());
            student.calculateAge();
            studentService.saveStudent(student);
            return ResponseEntity.ok(student);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping(value = "between-ages")
    public ResponseEntity<List<Student>> getStudentsBetweenAges(@RequestParam int startAge, @RequestParam int endAge) {
        return ResponseEntity.ok(studentService.getStudentsBetweenAges(startAge, endAge));
    }
}
