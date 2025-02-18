package com.epam.campus;

import com.epam.campus.console.AppConfig;
import com.epam.campus.console.ConsoleView;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        ConsoleView consoleView = context.getBean(ConsoleView.class);
        consoleView.start();
        context.close();
    }
}