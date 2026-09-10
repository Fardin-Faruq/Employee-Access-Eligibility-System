package com.example.employeeaccess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EligibilityService {

    private static final List<String> AUTHORIZED_DEPARTMENTS =
            Arrays.asList("IT", "HR", "FINANCE", "ADMINISTRATION");

    public EligibilityResult checkEligibility(Employee employee) {

        List<String> reasons = new ArrayList<>();

        // Age check
        if (employee.getAge() < 21) {
            reasons.add("Employee must be at least 21 years old.");
        }

        // Department check
        String department = employee.getDepartment().trim().toUpperCase();

        if (!AUTHORIZED_DEPARTMENTS.contains(department)) {
            reasons.add("Department is not authorized.");
        }

        // Employment status check
        if (!employee.getEmploymentStatus().equalsIgnoreCase("ACTIVE")) {
            reasons.add("Employment status is not active.");
        }

        // Employee ID check
        if (!employee.isIdValid()) {
            reasons.add("Employee ID is invalid.");
        }

        // Security clearance check
        boolean clearanceValid = hasRequiredClearance(
                employee.getSecurityClearance(),
                employee.getRequestedAccess()
        );

        if (!clearanceValid) {
            reasons.add("Security clearance is insufficient for requested access.");
        }

        /*
         * If any basic requirement other than clearance fails,
         * employee is Not Eligible.
         */
        boolean basicRequirementsFailed =
                employee.getAge() < 21 ||
                !AUTHORIZED_DEPARTMENTS.contains(department) ||
                !employee.getEmploymentStatus().equalsIgnoreCase("ACTIVE") ||
                !employee.isIdValid();

        if (basicRequirementsFailed) {
            return new EligibilityResult("NOT ELIGIBLE", reasons);
        }

        /*
         * Basic requirements are satisfied but clearance
         * is insufficient.
         */
        if (!clearanceValid) {
            return new EligibilityResult("CONDITIONALLY ELIGIBLE", reasons);
        }

        return new EligibilityResult("ELIGIBLE", reasons);
    }

    private boolean hasRequiredClearance(String clearance, String access) {

        int clearanceLevel = getLevel(clearance);
        int accessLevel = getLevel(access);

        return clearanceLevel >= accessLevel;
    }

    private int getLevel(String level) {

        switch (level.trim().toUpperCase()) {

            case "PUBLIC":
                return 1;

            case "INTERNAL":
                return 2;

            case "CONFIDENTIAL":
                return 3;

            case "SECRET":
                return 4;

            default:
                return 0;
        }
    }
}