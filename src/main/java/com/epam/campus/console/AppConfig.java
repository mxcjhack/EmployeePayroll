package com.epam.campus.console;

import com.epam.campus.dao.CollectionDataStore;
import com.epam.campus.dao.DataStore;
import com.epam.campus.service.DefaultEmployeeService;
import com.epam.campus.service.DefaultSalaryCalculator;
import com.epam.campus.service.EmployeeService;
import com.epam.campus.service.SalaryCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ComponentScan(basePackages = "com.epam.campus")
public class AppConfig {

    @Bean
    public DataStore dataStore(){
        return new CollectionDataStore();
    }

    @Bean
    public SalaryCalculator salaryCalculator(){
        return new DefaultSalaryCalculator();
    }

    @Bean
    @Primary
    public EmployeeService employeeService(DataStore dataStore, SalaryCalculator salaryCalculator) {
        return new DefaultEmployeeService(dataStore, salaryCalculator);
    }

    @Bean
    public ConsoleView consoleView(EmployeeService employeeService, ConsoleService consoleService) {
        return new ConsoleView(employeeService, consoleService);
    }

    @Bean
    public ConsoleService consoleService() {
        return new ConsoleService();
    }
}
