package com.epam.campus.console;

import com.epam.campus.model.Department;
import com.epam.campus.model.Designation;
import com.epam.campus.model.Employee;
import com.epam.campus.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class ConsoleView {
    private final EmployeeService employeeService;
    private final ConsoleService consoleService;

    @Autowired
    public ConsoleView(EmployeeService employeeService, ConsoleService consoleService) {
        this.employeeService = employeeService;
        this.consoleService = consoleService;
    }

    public void start() {
        System.out.println("Hello User");
        System.out.println("What would you like to do");

        boolean running = true;

        while (running) {
            System.out.println("""
                1. Add Employee
                2. View All Employees
                3. Update Employee Info
                4. Delete an Employee
                5. Get Payroll Report by Department
                6. Get Payroll Report of an Employee
                7. Exit
            """);

            try {
                int choice = consoleService.readInt("Enter your choice: ");
                switch (choice) {
                    case 1 -> addEmployee();
                    case 2 -> readEmployees();
                    case 3 -> updateEmployee();
                    case 4 -> deleteEmployee();
                    case 5 -> payrollByDepartment();
                    case 6 -> payrollByID();
                    case 7 -> {
                        System.out.println("Exiting application. Goodbye!");
                        running = false;
                    }
                    default -> System.out.println("Invalid Choice");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void addEmployee() {
        System.out.println("Enter Employee Details:");
        String name = consoleService.readString("Name: ");
        int age = consoleService.readInt("Age: ");
        LocalDate dateOfJoining = consoleService.readDate("Enter Date of Joining (yyyy-MM-dd): ", "yyyy-MM-dd");
        String gender = consoleService.readString("Gender (M/F/O): ");

        System.out.println("Departments are:");
        List<Department> departments = employeeService.getAllDepartments();
        departments.forEach(d -> System.out.println(d.getId() + ". " + d.getName()));

        int departmentId;
        Department department;
        while (true) {
            departmentId = consoleService.readInt("Select Department (Enter number): ");
            int finalDepartmentId = departmentId;
            department = departments.stream()
                    .filter(d -> d.getId() == finalDepartmentId)
                    .findFirst()
                    .orElse(null);
            if (department != null) {
                break;
            }
            System.out.println("Invalid Department ID. Please try again.");
        }

        System.out.println("Designations are:");
        List<Designation> designations = employeeService.getAllDesignations();
        designations.forEach(d -> System.out.println(d.getId() + ". " + d.getName()));

        int designationId;
        Designation designation;
        while (true) {
            designationId = consoleService.readInt("Select Designation (Enter number): ");
            int finalDesignationId = designationId;
            designation = designations.stream()
                    .filter(d -> d.getId() == finalDesignationId)
                    .findFirst()
                    .orElse(null);
            if (designation != null) {
                break;
            }
            System.out.println("Invalid Designation ID. Please try again.");
        }

        Employee employee = new Employee(name, age, dateOfJoining, gender, department, designation);
        System.out.println(employeeService.addEmployee(employee));
    }

    private void readEmployees() {
        employeeService.readEmployees().forEach(System.out::println);
    }

    private void updateEmployee() {
        int idForUpdate = consoleService.readInt("Enter Employee ID to update: ");
        System.out.println("""
            What do you want to update?
            1. Name
            2. Age
            3. Date of Joining
            4. Gender
            5. Department
            6. Designation
            """);
        int fieldToUpdate = consoleService.readInt("Enter choice: ");
        String newValue = consoleService.readString("Enter new value: ");

        System.out.println(employeeService.updateEmployee(idForUpdate, fieldToUpdate, newValue));
    }

    private void deleteEmployee() {
        int idForDelete = consoleService.readInt("Enter Employee ID to delete: ");
        System.out.println(employeeService.deleteEmployee(idForDelete));
    }

    private void payrollByDepartment() {
        System.out.println("Departments are:");
        List<Department> departments = employeeService.getAllDepartments();
        departments.forEach(d -> System.out.println(d.getId() + ". " + d.getName()));
        int departmentId = consoleService.readInt("Select Department (Enter number): ");
        Department department = departments.stream()
                .filter(d -> d.getId() == departmentId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid Department ID"));

        System.out.println(employeeService.payrollByDepartment(department));
    }

    private void payrollByID() {
        int idForPayroll = consoleService.readInt("Enter Employee ID for payroll details: ");
        System.out.println(employeeService.payrollByID(idForPayroll));
    }
}