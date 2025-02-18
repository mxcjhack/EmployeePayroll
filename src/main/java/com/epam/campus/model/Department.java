package com.epam.campus.model;

import java.util.Objects;

public class Department {
	private String departmentName;

    public Department(String departmentName) {
		setDepartmentName(departmentName);
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        if(departmentName == null || departmentName.isEmpty()) throw new IllegalArgumentException("Department Name Should not be null or Empty");
		this.departmentName = departmentName;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return Objects.equals(departmentName, that.departmentName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(departmentName);
    }

    @Override
    public String toString() {
        return departmentName;
    }
}
