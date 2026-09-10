package com.example.employeeaccess;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        EligibilityService service = new EligibilityService();

        try {

            System.out.print("Enter number of employees: ");
            int count = Integer.parseInt(scanner.nextLine());

            if (count <= 0) {
                throw new IllegalArgumentException(
                        "Number of employees must be greater than 0."
                );
            }

            for (int i = 1; i <= count; i++) {

                System.out.println("\n========== Employee " + i + " ==========");

                System.out.print("Employee ID: ");
                String id = scanner.nextLine();

                System.out.print("Name: ");
                String name = scanner.nextLine();

                System.out.print("Age: ");
                int age = Integer.parseInt(scanner.nextLine());

                if (age < 0 || age > 120) {
                    throw new IllegalArgumentException("Invalid age.");
                }

                System.out.print("Department: ");
                String department = scanner.nextLine();

                System.out.print("Employment Type (Permanent/Contract): ");
                String employmentType = scanner.nextLine();

                System.out.print("Employment Status (Active/Inactive): ");
                String employmentStatus = scanner.nextLine();

                System.out.print(
                        "Security Clearance (Public/Internal/Confidential/Secret): "
                );
                String securityClearance = scanner.nextLine();

                System.out.print("Is Employee ID valid? (Yes/No): ");
                String idInput = scanner.nextLine();

                boolean idValid;

                if (idInput.equalsIgnoreCase("yes")) {
                    idValid = true;
                } else if (idInput.equalsIgnoreCase("no")) {
                    idValid = false;
                } else {
                    throw new IllegalArgumentException(
                            "ID validity must be Yes or No."
                    );
                }

                System.out.print(
                        "Requested Access (Public/Internal/Confidential/Secret): "
                );
                String requestedAccess = scanner.nextLine();

                Employee employee = new Employee(
                        id,
                        name,
                        age,
                        department,
                        employmentType,
                        employmentStatus,
                        securityClearance,
                        idValid,
                        requestedAccess
                );

                EligibilityResult result =
                        service.checkEligibility(employee);

                System.out.println("\nResult for " + employee.getName());
                System.out.println("Status: " + result.getStatus());

                if (!result.getReasons().isEmpty()) {

                    System.out.println("Reasons:");

                    for (String reason : result.getReasons()) {
                        System.out.println("- " + reason);
                    }
                }
            }

        } catch (NumberFormatException e) {

            System.out.println(
                    "Invalid input. Please enter numbers where required."
            );

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "Input Error: " + e.getMessage()
            );

        } finally {

            scanner.close();
        }
    }
}