package com.example.demomidtermtest;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class EmployeeTableViewController implements Initializable {

    @FXML
    private CheckBox checkIT;

    @FXML
    private CheckBox checkSenior;

    @FXML
    private TableColumn<Employee, Integer> colEmployeeId;

    @FXML
    private TableColumn<Employee, String> colFirstName;

    @FXML
    private TableColumn<Employee, Date> colHireDate;

    @FXML
    private TableColumn<Employee, String> colJobCode;

    @FXML
    private TableColumn<Employee, String> colLastName;

    @FXML
    private TableColumn<Employee, String> colPhoneNumber;

    @FXML
    private ComboBox<String> combAreaCode;

    @FXML
    private Label lableTotal;

    @FXML
    private TableView<Employee> tableEmployees;

    /*
     * When start the window, show all employees and initialize the combobox list
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        colEmployeeId.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("employeeId"));
        colFirstName.setCellValueFactory(new PropertyValueFactory<Employee, String>("firstName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<Employee, String>("lastName"));
        colPhoneNumber.setCellValueFactory(new PropertyValueFactory<Employee, String>("phoneNumber"));
        colJobCode.setCellValueFactory(new PropertyValueFactory<Employee, String>("jobCode"));
        colHireDate.setCellValueFactory(new PropertyValueFactory<Employee, Date>("hireDate"));

        combAreaCode.getItems().clear();
        combAreaCode.getItems().add("All");

        DBUtility dbUtility = new DBUtility();

        try {
            ArrayList<String> areaCodes = dbUtility.getAreaCodes();
            combAreaCode.getItems().addAll(areaCodes);

            ArrayList<Employee> employees = dbUtility.getEmployees();
            tableEmployees.getItems().setAll(employees);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    /*
     * Update this method to update the TableView control
     */
    @FXML
    public void updateTableView() throws SQLException {
        boolean isSenior = checkSenior.isSelected();
        boolean isIT = checkIT.isSelected();
        String areaCode = combAreaCode.getValue();

        ArrayList<Employee> filteredEmployees = DBUtility.filterEmployees(isSenior, isIT, areaCode);

        tableEmployees.getItems().setAll(filteredEmployees);
        lableTotal.setText(String.valueOf(filteredEmployees.size()));
    }
}
