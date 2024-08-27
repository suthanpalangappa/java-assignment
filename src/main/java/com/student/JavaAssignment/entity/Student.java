package com.student.JavaAssignment.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Table(name = "STUDENT")
@Data
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STUDENT_ID")
    private Long studentId;
    @Column(name = "NAME")
    private String name;

    @Column(name = "DOB_DAY")
    private int dobDay;

    @Column(name = "DOB_MONTH")
    private int dobMonth;

    @Column(name = "DOB_YEAR")
    private int dobYear;
    @Column(name = "AGE")
    private int age;

    public void calculateAge() {
        LocalDate birthDate = LocalDate.of(this.dobYear, this.dobMonth, this.dobDay);
        this.age = Period.between(birthDate, LocalDate.now()).getYears();
    }
}
