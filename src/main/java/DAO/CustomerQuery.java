package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customers;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public abstract class CustomerQuery {
    public static ObservableList<Customers> getAllCustomers() {
        ObservableList<Customers> customersObservableList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM customers cu LEFT OUTER JOIN first_level_divisions f ON cu.Division_ID = f.Division_ID LEFT OUTER JOIN countries co ON f.Country_ID = co.Country_ID;";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String customerName = rs.getString("Customer_Name");
                String customerAddress = rs.getString("Address");
                String customerPostalCode = rs.getString("Postal_Code");
                String customerPhoneNumber = rs.getString("Phone");
                int customerId = rs.getInt("Customer_ID");
                int divisionId = rs.getInt("Division_ID");
                String country = rs.getString("Country");
                String firstLevelDivision = rs.getString("Division");

                Customers customer = new Customers(customerName, customerAddress, customerPostalCode, customerPhoneNumber, customerId, divisionId, country, firstLevelDivision);

                customersObservableList.add(customer);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return customersObservableList;
    }

    public static void addCustomer(String name, String address, String postalCode, String phone, Integer divisionId) {
        try {
            String sql = "INSERT INTO customers VALUES (NULL,?,?,?,?,?,?,?,?,?)";
            Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, address);
            ps.setString(3, postalCode);
            ps.setString(4, phone);
            ps.setTimestamp(5, timestamp);
            ps.setString(6, "QAM2");
            ps.setTimestamp(7, timestamp);
            ps.setString(8, "QAM2");
            ps.setInt(9, divisionId);

            ps.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
