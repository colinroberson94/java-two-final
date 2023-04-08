package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointments;
import model.CurrUser;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public abstract class AppointmentQuery {

    /**
     * Get all appointments from the database, create corresponding objects, add them to an observable list, and then return this list.
     *
     * @return ObservableList containing all appointments in the database
     */
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

    /**
     * Add new appointment to the database.
     *
     * @param title title of the appointment being added
     * @param description description of the appointment being added
     * @param location location of the appointment being added
     * @param type type of appointment being added
     * @param appointmentStart timestamp of the appointment start time
     * @param appointmentEnd timestamp of the appointment end time
     * @param userId user ID of the appointment being added
     * @param customerId customer ID of the appointment being added
     * @param contactId contact ID of the appointment being added
     */
    public static void addAppointment(String title, String description, String location, String type, LocalDateTime appointmentStart,
                                      LocalDateTime appointmentEnd, Integer userId, Integer customerId, Integer contactId) {
        try {
            CurrUser currUser = CurrUser.getCurrUser();

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
            ps.setString(8, currUser.getUserName());
            ps.setTimestamp(9, timestamp);
            ps.setString(10, currUser.getUserName());
            ps.setInt(11, customerId);
            ps.setInt(12, userId);
            ps.setInt(13, contactId);

            ps.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Delete an existing appointment from the database based on the supplied Appointment ID
     *
     * @param appointmentId appointment ID of the appointment that is to be updated
     */
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

    /**
     * Delete an existing appointment from the database based on the supplied Customer ID
     *
     * @param customerId appointment ID of the appointment that is to be updated
     */
    public static void deleteCustomerAppointments(Integer customerId) {
        try {
            String sql = "DELETE FROM appointments WHERE Customer_ID = ?";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ps.setInt(1, customerId);

            ps.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Update an existing appointment in the database.
     *
     * @param title title of the appointment being updated
     * @param description description of the appointment being updated
     * @param location location of the appointment being updated
     * @param type type of appointment being updated
     * @param appointmentStart timestamp of the appointment start time
     * @param appointmentEnd timestamp of the appointment end time
     * @param userId user ID of the appointment being updated
     * @param customerId customer ID of the appointment being updated
     * @param contactId contact ID of the appointment being updated
     * @param appointmentId appointment ID of the appointment that is to be updated
     */
    public static void updateAppointment(String title, String description, String location, String type, LocalDateTime appointmentStart,
                                         LocalDateTime appointmentEnd, Integer userId, Integer customerId, Integer contactId, Integer appointmentId) {
        try {
            CurrUser currUser = CurrUser.getCurrUser();

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
            ps.setString(8, currUser.getUserName());
            ps.setInt(9, customerId);
            ps.setInt(10, userId);
            ps.setInt(11, contactId);
            ps.setInt(12, appointmentId);

            ps.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Get appointments for the current week from the database, create corresponding objects, add them to an observable list, and then return this list.
     *
     * @return ObservableList containing the current week's appointments
     */
    public static ObservableList<Appointments> getWeeklyAppointments() {
        ObservableList<Appointments> weeklyAppointmentsObservableList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM appointments WHERE WEEK (start) = WEEK(current_date) AND YEAR(start) = YEAR(current_date);";
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

                weeklyAppointmentsObservableList.add(appt);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return weeklyAppointmentsObservableList;
    }

    /**
     * Get appointments for the current month from the database, create corresponding objects, add them to an observable list, and then return this list.
     *
     * @return ObservableList containing the current month's appointments
     */
    public static ObservableList<Appointments> getMonthlyAppointments() {
        ObservableList<Appointments> weeklyAppointmentsObservableList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM appointments WHERE MONTH(start) = MONTH(current_date) AND YEAR(start) = YEAR(current_date);";
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

                weeklyAppointmentsObservableList.add(appt);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return weeklyAppointmentsObservableList;
    }

    /**
     * Get any appointments that are within the next fifteen minutes from the database based on the supplied User ID
     *
     * @param userId User ID of the user whose appointments are being retrieved
     * @return String containing appointment ID and appointment Start time
     */
    public static String getAppointmentsWithinFifteenMin(Integer userId) {
        try {
            String sql = "SELECT * FROM appointments WHERE start between now() and now() + interval 15 minute and User_ID = ?";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            if (rs.next() != false) {
                Integer appointmentId = rs.getInt("Appointment_ID");
                LocalDateTime appointmentStart = rs.getTimestamp("Start").toLocalDateTime();
                String output = "Appointment ID " + appointmentId + " begins at " + appointmentStart;
                return output;
            } else {
                return null;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }
}
