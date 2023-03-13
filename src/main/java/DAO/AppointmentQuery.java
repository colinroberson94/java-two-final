package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointments;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public abstract class AppointmentQuery {
    public static ObservableList<Appointments> getAllAppointments() {
        ObservableList<Appointments> appointmentsObservableList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM APPOINTMENTS";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int appointmentId = rs.getInt("Appointment_ID");
                String appointmentTitle = rs.getString("Title");
                String appointmentDescription = rs.getString("Description");
                String appointmentLocation = rs.getString("Location");
                String appointmentType = rs.getString("Type");
                LocalDateTime appointmentStart = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime appointmentEnd = rs.getTimestamp("End").toLocalDateTime();
                int userId = rs.getInt("User_ID");
                int customerId = rs.getInt("Customer_ID");
                int contactId = rs.getInt("Contact_ID");

                Appointments appt = new Appointments(appointmentId, appointmentTitle, appointmentDescription, appointmentLocation,
                        appointmentType, appointmentStart, appointmentEnd, userId, customerId, contactId);

                appointmentsObservableList.add(appt);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return appointmentsObservableList;
    }
}
