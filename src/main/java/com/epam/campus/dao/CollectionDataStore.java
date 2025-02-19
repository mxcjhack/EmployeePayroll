package com.epam.campus.dao;

import com.epam.campus.model.*;
import com.epam.campus.service.DesignationFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Component
public class CollectionDataStore implements DataStore{
    private final List<Employee> employees = new ArrayList<>();
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public void addEmployee(Employee employee) {
        if(employee == null) throw new IllegalArgumentException();
        employees.add(employee);
    }

    @Override
    public List<Employee> giveEmployees() {
        return employees;
    }

    @Override
    public Employee getEmployeeById(int id) {
        return employees.stream()
                .filter(employee -> employee.getEmployeeID() == id)
                .findFirst().orElse(null);
    }

    @Override
    public void deleteEmployee(int id) {
        Employee employee = employees.stream()
                .filter(emp -> emp.getEmployeeID() == id)
                .findFirst().orElse(null);
        if(employee == null) {
            throw new NoSuchElementException("Employee Not found");
        } else {
            employees.remove(employee);
        }
    }

    @Override
    public void generateDefaultEmployees() {
        employees.add(new Employee(111, "Jiya Brein", 32, LocalDate.parse("2011-01-01", DATE_FORMATTER), "Female",
                new Department("HR"), DesignationFactory.createDesignationByID(1)));
        employees.add(new Employee(122, "Paul Niksui", 25, LocalDate.parse("2015-01-01", DATE_FORMATTER), "Male",
                new Department("Sales And Marketing"), DesignationFactory.createDesignationByID(1)));
        employees.add(new Employee(133, "Martin Theron", 29, LocalDate.parse("2012-01-01", DATE_FORMATTER), "Male",
                new Department("Infrastructure"), DesignationFactory.createDesignationByID(2)));
        employees.add(new Employee(144, "Murali Gowda", 28, LocalDate.parse("2014-01-01", DATE_FORMATTER), "Male",
                new Department("Product Development"), DesignationFactory.createDesignationByID(2)));
        employees.add(new Employee(155, "Nima Roy", 27, LocalDate.parse("2013-01-01", DATE_FORMATTER), "Female",
                new Department("HR"), DesignationFactory.createDesignationByID(2)));
        employees.add(new Employee(166, "Iqbal Hussain", 43, LocalDate.parse("2016-01-01", DATE_FORMATTER), "Male",
                new Department("Security And Transport"), DesignationFactory.createDesignationByID(1)));
        employees.add(new Employee(177, "Manu Sharma", 35, LocalDate.parse("2010-01-01", DATE_FORMATTER), "Male",
                new Department("Account And Finance"), DesignationFactory.createDesignationByID(3)));
        employees.add(new Employee(188, "Wang Liu", 31, LocalDate.parse("2015-01-01", DATE_FORMATTER), "Male",
                new Department("Product Development"), DesignationFactory.createDesignationByID(3)));
        employees.add(new Employee(199, "Amelia Zoe", 24, LocalDate.parse("2016-01-01", DATE_FORMATTER), "Female",
                new Department("Sales And Marketing"), DesignationFactory.createDesignationByID(1)));
        employees.add(new Employee(200, "Jaden Dough", 38, LocalDate.parse("2015-01-01", DATE_FORMATTER), "Male",
                new Department("Security And Transport"), DesignationFactory.createDesignationByID(1)));
        employees.add(new Employee(211, "Jasna Kaur", 27, LocalDate.parse("2014-01-01", DATE_FORMATTER), "Female",
                new Department("Infrastructure"), DesignationFactory.createDesignationByID(2)));
        employees.add(new Employee(222, "Nitin Joshi", 25, LocalDate.parse("2016-01-01", DATE_FORMATTER), "Male",
                new Department("Product Development"), DesignationFactory.createDesignationByID(2)));
        employees.add(new Employee(233, "Jyothi Reddy", 27, LocalDate.parse("2013-01-01", DATE_FORMATTER), "Female",
                new Department("Account And Finance"), DesignationFactory.createDesignationByID(2)));
        employees.add(new Employee(244, "Nicolus Den", 24, LocalDate.parse("2017-01-01", DATE_FORMATTER), "Male",
                new Department("Sales And Marketing"), DesignationFactory.createDesignationByID(1)));
        employees.add(new Employee(255, "Ali Baig", 23, LocalDate.parse("2018-01-01", DATE_FORMATTER), "Male",
                new Department("Infrastructure"), DesignationFactory.createDesignationByID(1)));
        employees.add(new Employee(266, "Sanvi Pandey", 26, LocalDate.parse("2015-01-01", DATE_FORMATTER), "Female",
                new Department("Product Development"), DesignationFactory.createDesignationByID(2)));
        employees.add(new Employee(277, "Anuj Chettiar", 31, LocalDate.parse("2012-01-01", DATE_FORMATTER), "Male",
                new Department("Product Development"), DesignationFactory.createDesignationByID(4)));
    }
}