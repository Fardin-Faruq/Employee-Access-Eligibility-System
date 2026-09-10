package com.example.employeeaccess;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EligibilityServiceTest {

    private final EligibilityService service =
            new EligibilityService();

    @Test
    void eligibleEmployee() {

        Employee employee = new Employee(
                "EMP001",
                "Arun",
                25,
                "IT",
                "Permanent",
                "Active",
                "Secret",
                true,
                "Confidential"
        );

        EligibilityResult result =
                service.checkEligibility(employee);

        assertEquals("ELIGIBLE", result.getStatus());
        assertTrue(result.getReasons().isEmpty());
    }

    @Test
    void ageBoundary21ShouldBeEligible() {

        Employee employee = new Employee(
                "EMP002",
                "Rahul",
                21,
                "HR",
                "Permanent",
                "Active",
                "Confidential",
                true,
                "Internal"
        );

        EligibilityResult result =
                service.checkEligibility(employee);

        assertEquals("ELIGIBLE", result.getStatus());
    }

    @Test
    void underAgeEmployeeShouldBeRejected() {

        Employee employee = new Employee(
                "EMP003",
                "Kiran",
                20,
                "IT",
                "Permanent",
                "Active",
                "Secret",
                true,
                "Internal"
        );

        EligibilityResult result =
                service.checkEligibility(employee);

        assertEquals("NOT ELIGIBLE", result.getStatus());

        assertTrue(
                result.getReasons()
                        .contains("Employee must be at least 21 years old.")
        );
    }

    @Test
    void unauthorizedDepartmentShouldBeRejected() {

        Employee employee = new Employee(
                "EMP004",
                "Vijay",
                30,
                "Sales",
                "Permanent",
                "Active",
                "Secret",
                true,
                "Internal"
        );

        EligibilityResult result =
                service.checkEligibility(employee);

        assertEquals("NOT ELIGIBLE", result.getStatus());

        assertTrue(
                result.getReasons()
                        .contains("Department is not authorized.")
        );
    }

    @Test
    void inactiveEmployeeShouldBeRejected() {

        Employee employee = new Employee(
                "EMP005",
                "Ravi",
                30,
                "Finance",
                "Permanent",
                "Inactive",
                "Secret",
                true,
                "Internal"
        );

        EligibilityResult result =
                service.checkEligibility(employee);

        assertEquals("NOT ELIGIBLE", result.getStatus());

        assertTrue(
                result.getReasons()
                        .contains("Employment status is not active.")
        );
    }

    @Test
    void invalidIdShouldBeRejected() {

        Employee employee = new Employee(
                "EMP006",
                "Suresh",
                30,
                "Administration",
                "Contract",
                "Active",
                "Secret",
                false,
                "Internal"
        );

        EligibilityResult result =
                service.checkEligibility(employee);

        assertEquals("NOT ELIGIBLE", result.getStatus());

        assertTrue(
                result.getReasons()
                        .contains("Employee ID is invalid.")
        );
    }

    @Test
    void insufficientClearanceShouldBeConditional() {

        Employee employee = new Employee(
                "EMP007",
                "Manoj",
                30,
                "IT",
                "Permanent",
                "Active",
                "Internal",
                true,
                "Secret"
        );

        EligibilityResult result =
                service.checkEligibility(employee);

        assertEquals(
                "CONDITIONALLY ELIGIBLE",
                result.getStatus()
        );

        assertTrue(
                result.getReasons()
                        .contains(
                                "Security clearance is insufficient for requested access."
                        )
        );
    }

    @Test
    void multipleFailuresShouldShowAllReasons() {

        Employee employee = new Employee(
                "EMP008",
                "Unknown",
                18,
                "Sales",
                "Contract",
                "Inactive",
                "Public",
                false,
                "Secret"
        );

        EligibilityResult result =
                service.checkEligibility(employee);

        assertEquals("NOT ELIGIBLE", result.getStatus());

        assertEquals(5, result.getReasons().size());

        assertTrue(
                result.getReasons()
                        .contains("Employee must be at least 21 years old.")
        );

        assertTrue(
                result.getReasons()
                        .contains("Department is not authorized.")
        );

        assertTrue(
                result.getReasons()
                        .contains("Employment status is not active.")
        );

        assertTrue(
                result.getReasons()
                        .contains("Employee ID is invalid.")
        );

        assertTrue(
                result.getReasons()
                        .contains(
                                "Security clearance is insufficient for requested access."
                        )
        );
    }
}