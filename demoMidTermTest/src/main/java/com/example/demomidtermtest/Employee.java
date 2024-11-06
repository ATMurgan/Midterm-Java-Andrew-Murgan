package com.example.demomidtermtest;

import java.util.Date;

public class Employee {
    private int employeeId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Date hireDate;
    private String jobCode;

    public Employee(int employeeId, String firstName, String lastName, String phoneNumber, Date hireDate, String jobCode) {
        setEmployeeId(employeeId);
        setFirstName(firstName);
        setLastName(lastName);
        setPhoneNumber(phoneNumber);
        setHireDate(hireDate);
        setJobCode(jobCode);
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName == null || firstName.length() <= 1) {
            throw new IllegalArgumentException("First name must be more than 1 character long.");
        }
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName == null || lastName.length() <= 1) {
            throw new IllegalArgumentException("Last name must be more than 1 character long.");
        }
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber.matches("^\\d{3}\\.\\d{3}\\.\\d{4}$")) {
            this.phoneNumber = phoneNumber;
        } else {
            throw new IllegalArgumentException("Phone number must match the format: xxx.xxx.xxxx.");
        }
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        if (hireDate != null && hireDate.after(new Date())) {
            throw new IllegalArgumentException("Hire date shouldn't be a date in the future.");
        }
        this.hireDate = hireDate;
    }

    public String getJobCode() {
        return jobCode;
    }

    public void setJobCode(String jobCode) {
        int count = 0;
        for (char c : jobCode.toCharArray()) {
            if (c == '_') {
                count++;
            } else if (!Character.isUpperCase(c)) {
                throw new IllegalArgumentException("All characters in the job code should be upper case");
            }
        }
        if (count != 1) {
            throw new IllegalArgumentException("Job code must have a '_' ");
        }
        this.jobCode = jobCode;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", hireDate=" + hireDate +
                ", jobCode='" + jobCode + '\'' +
                '}';
    }
}