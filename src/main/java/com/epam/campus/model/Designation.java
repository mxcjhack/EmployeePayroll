package com.epam.campus.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@NoArgsConstructor
public class Designation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @JsonIgnore
    private double baseSalary;

    @JsonIgnore
    private double bonusPercentage;

    // Parameterized constructor
    public Designation(String name, double baseSalary, double bonusPercentage) {
        this.name = name;
        this.baseSalary = baseSalary;
        this.bonusPercentage = bonusPercentage;
    }


}