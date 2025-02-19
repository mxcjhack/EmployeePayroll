package com.epam.campus.console;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

@Service
public class ConsoleService {
    private final Scanner scanner = new Scanner(System.in);

    public int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
    }

    public String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public LocalDate readDate(String prompt, String pattern) {
        while (true) {
            System.out.print(prompt);
            String dateString = scanner.nextLine();
            try {
                return LocalDate.parse(dateString, DateTimeFormatter.ofPattern(pattern));
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please enter the date in the format " + pattern + ".");
            }
        }
    }

    public void close() {
        scanner.close();
    }
}