package com.epam.campus.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
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

    // Default constructor (required by JPA)
    public Employee() {
    }

    // Parameterized constructor
    public Employee(String name, int age, LocalDate dateOfJoining, String gender, Department department, Designation designation) {
        setName(name);
        setAge(age);
        setDateOfJoining(dateOfJoining);
        setGender(gender);
        setDepartment(department);
        setDesignation(designation);
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) throw new IllegalArgumentException("Name cannot be empty or null");
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age < 22) throw new IllegalArgumentException("Person needs to be older than 21");
        this.age = age;
    }

    public LocalDate getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(LocalDate dateOfJoining) {
        if (dateOfJoining == null) throw new IllegalArgumentException("Date of Joining cannot be null");
        this.dateOfJoining = dateOfJoining;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        if (gender == null || gender.isEmpty()) throw new IllegalArgumentException("Gender cannot be null or empty");
        this.gender = gender;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        if (department == null) throw new IllegalArgumentException("Department cannot be null");
        this.department = department;
    }

    public Designation getDesignation() {
        return designation;
    }

    public void setDesignation(Designation designation) {
        if (designation == null) throw new IllegalArgumentException("Designation cannot be null");
        this.designation = designation;
    }

    // equals(), hashCode(), and toString()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", dateOfJoining=" + dateOfJoining +
                ", gender='" + gender + '\'' +
                ", department=" + department +
                ", designation=" + designation +
                '}';
    }
}