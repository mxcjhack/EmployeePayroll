package com.epam.campus;

import com.epam.campus.console.ConsoleView;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmployeePayrollApplication {

    public static void main(String[] args) {
        var context = SpringApplication.run(EmployeePayrollApplication.class, args);
        var consoleView = context.getBean(ConsoleView.class);
        consoleView.start();
    }
}