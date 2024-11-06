package com.example.demomidtermtest;

import java.util.ArrayList;
import java.sql.*;

public class DBUtility {
    private static String user = "sql5736387";
    private static String password = "hlTX4D4fnl";
    private static String url = "jdbc:mysql://sql5.freesqldatabase.com:3306/sql5736387";

    /*
     * To Do: Update this method to get all or filtered Employees from the database
     */
    public static ArrayList<Employee> getEmployees(String... sqls) throws SQLException {
        ArrayList<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM employee";

        // if (sqls.length > 0) sql = sqls[0];

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet resultSet = stmt.executeQuery(sql)) {

            while (resultSet.next()) {
                int employeeId = resultSet.getInt("employeeId");
                String employeeFirstName = resultSet.getString("firstname");
                String employeeLastName = resultSet.getString("lastname");
                String employeePhoneNumber = resultSet.getString("phonenumber");
                Date employeeHireDate = resultSet.getDate("hiredate");
                String employeeJobCode = resultSet.getString("jobcode");

                Employee employee = new Employee(employeeId, employeeFirstName, employeeLastName, employeePhoneNumber, employeeHireDate, employeeJobCode);
                employees.add(employee);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    /*
     * To Do: Update this method to get the area codes for the combo Box list
     */
    public static ArrayList<String> getAreaCodes() {

        String sql2 = "SELECT DISTINCT SUBSTRING(phoneNumber, 1, 3) AS area_code FROM employee WHERE phoneNumber LIKE '___.___.____'";
        ArrayList<String> areaCodes = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet resultSet = stmt.executeQuery(sql2)) {
            while (resultSet.next()) {
                areaCodes.add(resultSet.getString("area_code"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return areaCodes;
    }

    /*
     * To Do: Update this method to get the area codes for the combo Box list
     */
    public static ArrayList<Employee> filterEmployees(boolean isSenior, boolean isIT, String areaCode) throws SQLException {
        ArrayList<Employee> employees = new ArrayList<>();
        Connection connection = DriverManager.getConnection(url, user, password);
        Statement statement = connection.createStatement();

        StringBuilder query = new StringBuilder("SELECT * FROM employee WHERE 1=1");
        if (isSenior) {
            query.append(" AND hireDate <= '2014-10-16'");
        }
        if (isIT) {
            query.append(" AND jobCode = 'IT_PROG'");
        }
        if (areaCode != null && !areaCode.equals("All")) {
            query.append(" AND phoneNumber LIKE '").append(areaCode).append("%'");
        }

        ResultSet resultSet = statement.executeQuery(query.toString());
        while (resultSet.next()) {
            Employee employee = new Employee(
                    resultSet.getInt("employeeId"),
                    resultSet.getString("firstName"),
                    resultSet.getString("lastName"),
                    resultSet.getString("phoneNumber"),
                    resultSet.getDate("hireDate"),
                    resultSet.getString("jobCode")
            );
            employees.add(employee);
        }

        return employees;
    }
}