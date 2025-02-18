package com.epam.campus.model;

import java.util.Objects;

public class Designation {
	private String designationName;
	private double baseSalary;
	private double bonusPercentage;

    public Designation(String designationName, double baseSalary, double bonusPercentage) {
        setDesignationName(designationName);
		setBaseSalary(baseSalary);
		setBonusPercentage(bonusPercentage);
    }

    public String getDesignationName() {
        return designationName;
    }

    public void setDesignationName(String designationName) {
        if(designationName == null || designationName.isEmpty()) throw new IllegalArgumentException("Designation name cannot be empty or null");
		this.designationName = designationName;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        if(baseSalary <= 0) throw new IllegalArgumentException("Salary cannot be 0 or less");
		this.baseSalary = baseSalary;
    }

    public double getBonusPercentage() {
        return bonusPercentage;
    }

    public void setBonusPercentage(double bonusPercentage) {
        if(bonusPercentage <= 0)throw new IllegalArgumentException("Bonus percentage cannot be 0 or less");
		this.bonusPercentage = bonusPercentage;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Designation that = (Designation) o;
        return Objects.equals(designationName, that.designationName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(designationName);
    }

    @Override
    public String toString() {
        return designationName;
    }
}
