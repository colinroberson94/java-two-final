package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Reports;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class ReportsQuery {

    /**
     * Get total appointments by type and month from the database, create objects from these records,
     * add them to an observable list, and then return this list.
     *
     * @return ObservableList containing total appointments by type and month.
     */
    public static ObservableList<Reports> getTotalAppointmentsByTypeAndMonth() {
        ObservableList<Reports> appointmentsByTypeAndMonth = FXCollections.observableArrayList();

        try {
            String sql = "SELECT Type, month(Start) as Month, Count(*) as Total FROM appointments GROUP By Month, Type;";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String type = rs.getString("Type");
                Integer month = rs.getInt("Month");
                Integer total = rs.getInt("Total");

                Reports report = new Reports(type, month, total);

                appointmentsByTypeAndMonth.add(report);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return appointmentsByTypeAndMonth;
    }

    /**
     * Get total appointments by customer from the database, create objects from these records,
     * add them to an observable list, and then return this list.
     *
     * @return ObservableList containing total appointments by customer.
     */
    public static ObservableList<Reports> getTotalAppointmentsByCustomer() {
        ObservableList<Reports> appointmentsByCustomer = FXCollections.observableArrayList();

        try {
            String sql = "SELECT Customer_Name, Count(*) as Total FROM appointments LEFT JOIN customers ON appointments.Customer_ID = customers.Customer_ID GROUP BY Customer_Name;";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String name = rs.getString("Customer_Name");
                Integer total = rs.getInt("Total");

                Reports report = new Reports(name, total);

                appointmentsByCustomer.add(report);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return appointmentsByCustomer;
    }
}
