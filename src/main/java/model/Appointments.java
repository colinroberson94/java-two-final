package model;

import java.time.LocalDateTime;

public class Appointments {
    private int appointmentId;
    private String appointmentTitle;
    private String appointmentDescription;
    private String appointmentLocation;
    private String appointmentType;
    public LocalDateTime appointmentStart;
    public LocalDateTime appointmentEnd;
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


}
