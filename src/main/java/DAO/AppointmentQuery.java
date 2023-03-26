package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointments;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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
                Integer userId = rs.getInt("User_ID");
                Integer customerId = rs.getInt("Customer_ID");
                Integer contactId = rs.getInt("Contact_ID");

                Appointments appt = new Appointments(appointmentId, appointmentTitle, appointmentDescription, appointmentLocation,
                        appointmentType, appointmentStart, appointmentEnd, userId, customerId, contactId);

                appointmentsObservableList.add(appt);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return appointmentsObservableList;
    }

    // TODO add ability to pull in current users name
    public static void addAppointment(String title, String description, String location, String type, LocalDateTime appointmentStart,
                                      LocalDateTime appointmentEnd, Integer userId, Integer customerId, Integer contactId) {
        try {
            String sql = "INSERT INTO appointments VALUES (NULL,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ps.setString(1, title);
            ps.setString(2, description);
            ps.setString(3, location);
            ps.setString(4, type);
            ps.setTimestamp(5, Timestamp.valueOf(appointmentStart));
            ps.setTimestamp(6, Timestamp.valueOf(appointmentEnd));
            ps.setTimestamp(7, timestamp);
            ps.setString(8, "QAM2");
            ps.setTimestamp(9, timestamp);
            ps.setString(10, "QAM2");
            ps.setInt(11, customerId);
            ps.setInt(12, userId);
            ps.setInt(13, contactId);

            ps.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void deleteAppointment(Integer appointmentId) {
        try {
            String sql = "DELETE FROM appointments WHERE Appointment_ID = ?";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ps.setInt(1, appointmentId);

            ps.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void updateAppointment(String title, String description, String location, String type, LocalDateTime appointmentStart,
                                         LocalDateTime appointmentEnd, Integer userId, Integer customerId, Integer contactId, Integer appointmentId) {
        try {
            String sql = "UPDATE appointments SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Last_Update = ?, Last_Updated_By = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE Appointment_ID = ?";
            Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ps.setString(1, title);
            ps.setString(2, description);
            ps.setString(3, location);
            ps.setString(4, type);
            ps.setTimestamp(5, Timestamp.valueOf(appointmentStart));
            ps.setTimestamp(6, Timestamp.valueOf(appointmentEnd));
            ps.setTimestamp(7, timestamp);
            ps.setString(8, "QAM2");
            ps.setInt(9, customerId);
            ps.setInt(10, userId);
            ps.setInt(11, contactId);
            ps.setInt(12, appointmentId);

            ps.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
