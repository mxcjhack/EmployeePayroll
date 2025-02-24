package com.epam.campus.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
public class Designation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @JsonIgnore
    private double baseSalary;

    @JsonIgnore
    private double bonusPercentage;

    // Default constructor (required by JPA)
    public Designation() {
    }

    // Parameterized constructor
    public Designation(String name, double baseSalary, double bonusPercentage) {
        this.name = name;
        this.baseSalary = baseSalary;
        this.bonusPercentage = bonusPercentage;
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
        if (name == null || name.isEmpty()) throw new IllegalArgumentException("Designation name cannot be null or empty");
        this.name = name;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        if (baseSalary <= 0) throw new IllegalArgumentException("Base salary cannot be 0 or less");
        this.baseSalary = baseSalary;
    }

    public double getBonusPercentage() {
        return bonusPercentage;
    }

    public void setBonusPercentage(double bonusPercentage) {
        if (bonusPercentage <= 0) throw new IllegalArgumentException("Bonus percentage cannot be 0 or less");
        this.bonusPercentage = bonusPercentage;
    }

    // equals(), hashCode(), and toString()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Designation that = (Designation) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Designation{" +
                ", name='" + name + '\'' +
                '}';
    }
}