package com.epam.campus.console;

import com.epam.campus.service.DefaultEmployeeService;
import com.epam.campus.service.EmployeeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ComponentScan(basePackages = "com.epam.campus")
public class AppConfig {

    @Bean
    @Primary
    public EmployeeService employeeService() {
        return new DefaultEmployeeService();
    }

    @Bean
    public ConsoleView consoleView(EmployeeService employeeService) {
        return new ConsoleView(employeeService);
    }
}
