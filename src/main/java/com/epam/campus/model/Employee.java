package com.epam.campus.model;

import java.time.LocalDate;
import java.util.Objects;

public class Employee {
    private int employeeID;
    private String name;
    private int age;
    private LocalDate dateOfJoining;
    private String gender;
    private Department department;
    private Designation designation;

    public Employee(int employeeID, String name, int age, LocalDate dateOfJoining, String gender, Department department, Designation designation) {
        setEmployeeID(employeeID);
        setName(name);
        setAge(age);
        setDateOfJoining(dateOfJoining);
        setGender(gender);
        setDepartment(department);
        setDesignation(designation);
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        if (employeeID <= 0) {
            throw new IllegalArgumentException("Employee ID cannot be 0 or negative");
        } else {
            this.employeeID = employeeID;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) throw new IllegalArgumentException("Name cannot be Empty or null");
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
        if (gender == null || gender.isEmpty()) throw new IllegalArgumentException("Gender cannot be null or Empty");
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
        if (designation == null) throw new IllegalArgumentException("Designation cannot be empty");
        this.designation = designation;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return employeeID == employee.employeeID;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(employeeID);
    }

    @Override
    public String toString() {
        return "employeeID=" + employeeID +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", dateOfJoining=" + dateOfJoining +
                ", gender='" + gender + '\'' +
                ", department=" + department +
                ", designation=" + designation;
    }
}