package com.epam.campus.console;

import com.epam.campus.model.Department;
import com.epam.campus.model.Designation;
import com.epam.campus.model.Employee;
import com.epam.campus.service.DepartmentFactory;
import com.epam.campus.service.DesignationFactory;
import com.epam.campus.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

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
                7. Get Default Employees
                8. Exit
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
                    case 7 -> defaultEmployees();
                    case 8 -> {
                        System.out.println("Exiting application. Goodbye!");
                        running = false;  // Exit the loop
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
        int id = consoleService.readInt("ID: ");
        String name = consoleService.readString("Name: ");
        int age = consoleService.readInt("Age: ");
        LocalDate dateOfJoining = consoleService.readDate("Enter Date of Joining (yyyy-MM-dd): ", "yyyy-MM-dd");
        String gender = consoleService.readString("Gender (M/F/O): ");

        System.out.println("Designations are:");
        System.out.println("1. Junior");
        System.out.println("2. Senior");
        System.out.println("3. Lead");
        System.out.println("4. Head");
        int designationID = consoleService.readInt("Select Designation (Enter number): ");

        System.out.println("Departments are:");
        System.out.println("1. HR");
        System.out.println("2. Sales And Marketing");
        System.out.println("3. Infrastructure");
        System.out.println("4. Product Development");
        System.out.println("5. Security And Transport");
        System.out.println("6. Account And Finance");
        int departmentID = consoleService.readInt("Select Department (Enter number): ");

        Designation designation = DesignationFactory.createDesignationByID(designationID);
        Department department = DepartmentFactory.createDepartment(departmentID);

        Employee employee = new Employee(id, name, age, dateOfJoining, gender, department, designation);
        System.out.println(employeeService.addEmployee(employee));
    }

    private void readEmployees() {
        System.out.println(employeeService.readEmployees());
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
        System.out.println("1. HR");
        System.out.println("2. Sales And Marketing");
        System.out.println("3. Infrastructure");
        System.out.println("4. Product Development");
        System.out.println("5. Security And Transport");
        System.out.println("6. Account And Finance");
        int departmentID = consoleService.readInt("Select Department (Enter number): ");
        Department department = DepartmentFactory.createDepartment(departmentID);
        System.out.println(employeeService.payrollByDepartment(department));
    }

    private void payrollByID() {
        int idForPayroll = consoleService.readInt("Enter Employee ID for payroll details: ");
        System.out.println(employeeService.payrollByID(idForPayroll));
    }

    private void defaultEmployees() {
        System.out.println(employeeService.defaultEmployees());
    }

    private void exitApplication() {
        System.out.println("Exiting...");
        consoleService.close();
        System.exit(0);
    }
}