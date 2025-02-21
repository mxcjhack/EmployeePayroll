package com.epam.campus.repository;

import com.epam.campus.model.Department;
import com.epam.campus.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    // Custom JPQL query to find employees by department
    @Query("SELECT e FROM Employee e WHERE e.department = :department")
    List<Employee> findByDepartment(Department department);
}