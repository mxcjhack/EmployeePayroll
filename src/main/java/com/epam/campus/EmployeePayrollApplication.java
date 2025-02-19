package com.epam.campus;

import com.epam.campus.console.ConsoleView;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmployeePayrollApplication implements CommandLineRunner {
    private final ConsoleView consoleView;

    public EmployeePayrollApplication(ConsoleView consoleView){
        this.consoleView = consoleView;
    }
    public static void main(String[] args) {
        SpringApplication.run(EmployeePayrollApplication.class, args);
    }

    @Override
    public void run(String... args){
        consoleView.start();
    }
}