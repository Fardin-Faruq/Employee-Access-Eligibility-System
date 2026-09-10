package com.example.employeeaccess;

public class Employee {

    private String employeeId;
    private String name;
    private int age;
    private String department;
    private String employmentType;
    private String employmentStatus;
    private String securityClearance;
    private boolean idValid;
    private String requestedAccess;

    public Employee(String employeeId, String name, int age,
                    String department, String employmentType,
                    String employmentStatus, String securityClearance,
                    boolean idValid, String requestedAccess) {

        this.employeeId = employeeId;
        this.name = name;
        this.age = age;
        this.department = department;
        this.employmentType = employmentType;
        this.employmentStatus = employmentStatus;
        this.securityClearance = securityClearance;
        this.idValid = idValid;
        this.requestedAccess = requestedAccess;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getDepartment() {
        return department;
    }

    public String getEmploymentType() {
        return employmentType;
    }

    public String getEmploymentStatus() {
        return employmentStatus;
    }

    public String getSecurityClearance() {
        return securityClearance;
    }

    public boolean isIdValid() {
        return idValid;
    }

    public String getRequestedAccess() {
        return requestedAccess;
    }
}