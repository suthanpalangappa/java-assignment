package com.student.JavaAssignment.repository;

import com.student.JavaAssignment.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByAgeBetween(int startAge, int endAge);
}
