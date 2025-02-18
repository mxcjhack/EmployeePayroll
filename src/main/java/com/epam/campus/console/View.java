package com.epam.campus.console;

import com.epam.campus.model.Department;
import com.epam.campus.model.Designation;
import com.epam.campus.model.Employee;
import com.epam.campus.service.DefaultEmployeePayrollSystem;
import com.epam.campus.service.DepartmentFactory;
import com.epam.campus.service.DesignationFactory;
import com.epam.campus.service.EmployeePayrollSystem;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

import java.util.Scanner;

/*
Get all printing statements in View layer
 */

public class View {
    private final Scanner scanner = new Scanner(System.in);
    private final EmployeePayrollSystem employeePayrollSystem = new DefaultEmployeePayrollSystem();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public void start() {
        System.out.println("Hello User");
        System.out.println("What would you like to do");

        while (true) {
            System.out.println("""
                1. Add Employee
                2. View All Employees
                3. Update Employee Info
                4. Delete an Employee
                5. Get Payroll Report by Department
                6. Get Payroll Report of an Employee
                7. Get Default Employees
                8. Exit
                """);

            try{
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1 -> addEmployee();
                    case 2 -> readEmployees();
                    case 3 -> updateEmployee();
                    case 4 -> deleteEmployee();
                    case 5 -> payrollByDepartment();
                    case 6 -> payrollByID();
                    case 7 -> defaultEmployees();
                    case 8 -> exitApplication();
                    default -> System.out.println("Invalid Choice");
                }
            } catch (Exception e){
                System.out.println(e.getMessage());
                continue;
            }
        }
    }

    private void addEmployee() {
        System.out.println("Enter Employee Details:");
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter Date of Joining (yyyy-MM-dd): ");
        LocalDate dateOfJoining = LocalDate.parse(scanner.nextLine(), formatter);

        System.out.print("Gender (M/F/O): ");
        String gender = scanner.nextLine();

        System.out.print("Designations are\n1. Junior\n2. Senior\n3. Lead\n4. Head. ");
        System.out.print("Designation: ");
        int designationID = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Departments are:");
        System.out.println("1. HR");
        System.out.println("2. Sales And Marketing");
        System.out.println("3. Infrastructure");
        System.out.println("4. Product Development");
        System.out.println("5. Security And Transport");
        System.out.println("6. Account And Finance");
        System.out.print("Select Department (Enter number 1-6): ");
        int departmentID = scanner.nextInt();

        Designation designation = DesignationFactory.createDesignationByID(designationID);
        Department department = DepartmentFactory.createDepartment(departmentID);

        Employee employee = new Employee(id, name, age, dateOfJoining, gender, department, designation);
        System.out.println(employeePayrollSystem.addEmployee(employee));
    }

    private void readEmployees(){
        System.out.println(employeePayrollSystem.readEmployees());
    }

    private void updateEmployee() {
        System.out.print("Enter Employee ID to update: ");
        int idForUpdate = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.println("""
            What do you want to update?
            1. Name
            2. Age
            3. Date of Joining
            4. Gender
            5. Department
            6. Designation
            """);
        System.out.print("Enter choice: ");
        int fieldToUpdate = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter new value: ");
        String newValue = scanner.nextLine();

        System.out.println(employeePayrollSystem.updateEmployee(idForUpdate, fieldToUpdate, newValue));
    }

    private void deleteEmployee() {
        System.out.println("Enter Employee ID to delete: ");
        int idForDelete = scanner.nextInt();
        System.out.println(employeePayrollSystem.deleteEmployee(idForDelete));
    }

    private void payrollByDepartment() {
        System.out.print("Enter department name for payroll details: ");
        System.out.println("Departments are:");
        System.out.println("1. HR");
        System.out.println("2. Sales And Marketing");
        System.out.println("3. Infrastructure");
        System.out.println("4. Product Development");
        System.out.println("5. Security And Transport");
        System.out.println("6. Account And Finance");
        System.out.print("Select Department (Enter number 1-6): ");
        int departmentID = scanner.nextInt();
        System.out.println(employeePayrollSystem.payrollByDepartment(DepartmentFactory.createDepartment(departmentID)));
    }

    private void payrollByID() {
        System.out.print("Enter Employee ID for payroll details: ");
        int idForPayroll = scanner.nextInt();
        System.out.println(employeePayrollSystem.payrollByID(idForPayroll));
    }

    private void defaultEmployees(){
        System.out.println(employeePayrollSystem.defaultEmployees());
    }

    private void exitApplication() {
        System.out.println("Exiting...");
        scanner.close();
        System.exit(0);
    }
}
