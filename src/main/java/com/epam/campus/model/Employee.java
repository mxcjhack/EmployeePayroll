package com.epam.campus.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Objects;


@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private int age;
    private LocalDate dateOfJoining;
    private String gender;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "designation_id")
    private Designation designation;


    public Employee(String name, int age, LocalDate dateOfJoining, String gender, Department department, Designation designation) {
        setName(name);
        setAge(age);
        setDateOfJoining(dateOfJoining);
        setGender(gender);
        setDepartment(department);
        setDesignation(designation);
    }

}