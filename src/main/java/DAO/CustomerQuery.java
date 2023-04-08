package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.CurrUser;
import model.Customers;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public abstract class CustomerQuery {

    /**
     * Get all customers from the database, create corresponding objects, add them to an observable list, and then return this list.
     *
     * @return ObservableList containing all customers in the database
     */
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

    /**
     * Add new customer to the database.
     *
     * @param name name of the customer being added
     * @param address address of the customer being added
     * @param postalCode postal code of the customer being added
     * @param phone phone number of the customer being added
     * @param divisionId First level Division of the customer
     */
    public static void addCustomer(String name, String address, String postalCode, String phone, Integer divisionId) {
        try {
            CurrUser currUser = CurrUser.getCurrUser();

            String sql = "INSERT INTO customers VALUES (NULL,?,?,?,?,?,?,?,?,?)";
            Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, address);
            ps.setString(3, postalCode);
            ps.setString(4, phone);
            ps.setTimestamp(5, timestamp);
            ps.setString(6, currUser.getUserName());
            ps.setTimestamp(7, timestamp);
            ps.setString(8, currUser.getUserName());
            ps.setInt(9, divisionId);

            ps.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Delete an existing customer from the database based on the supplied Customer ID
     *
     * @param customerId customer ID of the customer that is to be updated
     */
    public static void deleteCustomer(Integer customerId) {
        try {
            String sql = "DELETE FROM customers WHERE Customer_ID = ?";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ps.setInt(1, customerId.intValue());

            ps.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Update an existing customer in the database.
     *
     * @param name name of the customer being added
     * @param address address of the customer being added
     * @param postalCode postal code of the customer being added
     * @param phone phone number of the customer being added
     * @param divisionId First level Division of the customer
     * @param customerId customer ID of the customer that is to be updated
     */
    public static void updateCustomer(String name, String address, String postalCode, String phone, Integer divisionId, Integer customerId) {
        try {
            CurrUser currUser = CurrUser.getCurrUser();

            String sql = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Last_Update = ?, Last_Updated_By = ?, Division_ID = ? WHERE Customer_ID = ?";
            Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, address);
            ps.setString(3, postalCode);
            ps.setString(4, phone);
            ps.setTimestamp(5, timestamp);
            ps.setString(6, currUser.getUserName());
            ps.setInt(7, divisionId);
            ps.setInt(8, customerId);

            ps.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
