package model;

import java.time.LocalDateTime;

public class Appointments {
    private int appointmentId;
    private String appointmentTitle;
    private String appointmentDescription;
    private String appointmentLocation;
    private String appointmentType;
    private LocalDateTime appointmentStart;
    private LocalDateTime appointmentEnd;
    private int userId;
    private int customerId;
    private int contactId;

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

    public int getUserId() {
        return userId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getContactId() {
        return contactId;
    }

    public void setAppointmentId(int appointmentId) {
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

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

}
