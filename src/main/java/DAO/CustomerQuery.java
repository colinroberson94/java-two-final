package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class CustomerQuery {
    public static ObservableList<Customers> getAllCustomers() {
        ObservableList<Customers> customersObservableList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM CUSTOMERS";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String customerName = rs.getString("Customer_Name");
                String customerAddress = rs.getString("Address");
                String customerPostalCode = rs.getString("Postal_Code");
                String customerPhoneNumber = rs.getString("Phone");
                int customerId = rs.getInt("Customer_ID");

                Customers customer = new Customers(customerName, customerAddress, customerPostalCode, customerPhoneNumber, customerId);

                customersObservableList.add(customer);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return customersObservableList;
    }
}
