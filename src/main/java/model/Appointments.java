package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;

import static DAO.AppointmentQuery.getAllAppointments;

public class Appointments {
    private int appointmentId;
    private String appointmentTitle;
    private String appointmentDescription;
    private String appointmentLocation;
    private String appointmentType;
    private LocalDateTime appointmentStart;
    private LocalDateTime appointmentEnd;
    private Integer userId;
    private Integer customerId;
    private Integer contactId;
    private static ObservableList<Appointments> allAppointments = FXCollections.observableArrayList();

    public Appointments (int appointmentId, String appointmentTitle, String appointmentDescription,
                         String appointmentLocation, String appointmentType, LocalDateTime appointmentStart,
                         LocalDateTime appointmentEnd, int userId, int customerId, int contactID) {
        this.appointmentId = appointmentId;
        this.appointmentTitle = appointmentTitle;
        this.appointmentDescription = appointmentDescription;
        this.appointmentLocation = appointmentLocation;
        this.appointmentType = appointmentType;
        this.appointmentStart = appointmentStart;
        this.appointmentEnd = appointmentEnd;
        this.userId = userId;
        this.customerId = customerId;
        this.contactId = contactID;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public String getAppointmentTitle() {
        return appointmentTitle;
    }

    public String getAppointmentDescription() {
        return appointmentDescription;
    }

    public String getAppointmentLocation() {
        return appointmentLocation;
    }

    public String getAppointmentType() {
        return appointmentType;
    }

    public LocalDateTime getAppointmentStart() {
        return appointmentStart;
    }

    public LocalDateTime getAppointmentEnd() {
        return appointmentEnd;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public Integer getContactId() {
        return contactId;
    }

    public void setAppointmentId(Integer appointmentId) {
        this.appointmentId = appointmentId;
    }

    public void setAppointmentTitle(String appointmentTitle) {
        this.appointmentTitle = appointmentTitle;
    }

    public void setAppointmentDescription(String appointmentDescription) {
        this.appointmentDescription = appointmentDescription;
    }

    public void setAppointmentLocation(String appointmentLocation) {
        this.appointmentLocation = appointmentLocation;
    }

    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }

    public void setAppointmentStart(LocalDateTime appointmentStart) {
        this.appointmentStart = appointmentStart;
    }

    public void setAppointmentEnd(LocalDateTime appointmentEnd) {
        this.appointmentEnd = appointmentEnd;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }

    public static ObservableList<Appointments> getAppointmentsListByContact(Integer contactId) {
        allAppointments = getAllAppointments();
        ObservableList<Appointments> filteredAppointments = FXCollections.observableArrayList();

        for (Appointments appointment : allAppointments) {
            if (appointment.getContactId().equals(contactId)) {
                filteredAppointments.add(appointment);
            }
        }

        return filteredAppointments;
    }
    
}
