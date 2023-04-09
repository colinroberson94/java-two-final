package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.*;

import static DAO.AppointmentQuery.getAllAppointments;

/**
 * Class for instantiating Appointment objects.
 *
 * @author Colin Roberson
 */
public class Appointments {

    /**
     * The ID for the appointment.
     */
    private int appointmentId;

    /**
     * The title of the appointment.
     */
    private String appointmentTitle;

    /**
     * The description of the appointment.
     */
    private String appointmentDescription;

    /**
     * The location of the appointment.
     */
    private String appointmentLocation;

    /**
     * The type of the appointment.
     */
    private String appointmentType;

    /**
     * The start time of the appointment.
     */
    private LocalDateTime appointmentStart;

    /**
     * The end time of the appointment.
     */
    private LocalDateTime appointmentEnd;

    /**
     * The user ID of the appointment.
     */
    private Integer userId;

    /**
     * The customer ID of the appointment.
     */
    private Integer customerId;

    /**
     * The contact ID of the appointment.
     */
    private Integer contactId;

    /**
     * List of all Appointment objects
     */
    private static ObservableList<Appointments> allAppointments = FXCollections.observableArrayList();

    /**
     * Constructor for new instances of Appointment objects.
     *
     * @param appointmentId The ID for the appointment.
     * @param appointmentTitle The title of the appointment.
     * @param appointmentDescription The description of the appointment.
     * @param appointmentLocation The location of the appointment.
     * @param appointmentType The type of the appointment.
     * @param appointmentStart The start datetime of the appointment.
     * @param appointmentEnd The end datetime of the appointment.
     * @param userId The user ID associated with the appointment.
     * @param customerId The customer ID associated with the appointment.
     * @param contactID The contact ID associated with the appointment.
     */
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

    /**
     * Get Appointment ID
     *
     * @return Appointment ID
     */
    public int getAppointmentId() {
        return appointmentId;
    }

    /**
     * Get Appointment Title
     *
     * @return Appointment Title
     */
    public String getAppointmentTitle() {
        return appointmentTitle;
    }

    /**
     * Get Appointment Description
     *
     * @return Appointment Description
     */
    public String getAppointmentDescription() {
        return appointmentDescription;
    }

    /**
     * Get Appointment Location
     *
     * @return Appointment Location
     */
    public String getAppointmentLocation() {
        return appointmentLocation;
    }

    /**
     * Get Appointment Type
     *
     * @return Appointment Type
     */
    public String getAppointmentType() {
        return appointmentType;
    }

    /**
     * Get Appointment Start time
     *
     * @return Appointment Start time
     */
    public LocalDateTime getAppointmentStart() {
        return appointmentStart;
    }

    /**
     * Get Appointment End time
     *
     * @return Appointment End time
     */
    public LocalDateTime getAppointmentEnd() {
        return appointmentEnd;
    }

    /**
     * Get Appointment user ID
     *
     * @return Appointment User ID
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * Get Appointment Customer ID
     *
     * @return Appointment Customer ID
     */
    public Integer getCustomerId() {
        return customerId;
    }

    /**
     * Get Appointment Contact ID
     *
     * @return Appointment Contact ID
     */
    public Integer getContactId() {
        return contactId;
    }

    /**
     * Set the appointment ID
     *
     * @param appointmentId the id to set
     */
    public void setAppointmentId(Integer appointmentId) {
        this.appointmentId = appointmentId;
    }

    /**
     * Set the appointment title
     *
     * @param appointmentTitle the title to set
     */
    public void setAppointmentTitle(String appointmentTitle) {
        this.appointmentTitle = appointmentTitle;
    }

    /**
     * Set the appointment description
     *
     * @param appointmentDescription the description to set
     */
    public void setAppointmentDescription(String appointmentDescription) {
        this.appointmentDescription = appointmentDescription;
    }

    /**
     * Set the appointment location
     *
     * @param appointmentLocation the location to set
     */
    public void setAppointmentLocation(String appointmentLocation) {
        this.appointmentLocation = appointmentLocation;
    }

    /**
     * Set the appointment type
     *
     * @param appointmentType the type to set
     */
    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }

    /**
     * Set the appointment start time
     *
     * @param appointmentStart the start time to set
     */
    public void setAppointmentStart(LocalDateTime appointmentStart) {
        this.appointmentStart = appointmentStart;
    }

    /**
     * Set the appointment end time
     *
     * @param appointmentEnd the end time to set
     */
    public void setAppointmentEnd(LocalDateTime appointmentEnd) {
        this.appointmentEnd = appointmentEnd;
    }

    /**
     * Set the user ID
     *
     * @param userId the user ID to set
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * Set the customer ID
     *
     * @param customerId the customer ID to set
     */
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    /**
     * Set the contact ID
     *
     * @param contactId the contact ID to set
     */
    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }

    /**
     * Get all of a specific contact's appointments
     *
     * @param contactId the contact ID whose appointments will be returned
     * @return filtered appointment list.
     */
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

    /**
     * Check to see if an appointment is within business hours or not.
     *
     * @param start start time of an appointment
     * @param end end time of an appointment
     * @return true if the appointment is within business hours, otherwise return false.
     */
    public static boolean withinBusinessHours(LocalDateTime start, LocalDateTime end) {
        // ZoneId will be used to convert the provided times into EST
        ZoneId currZone = ZoneId.systemDefault();
        ZoneId estZone = ZoneId.of("US/Eastern");

        // Convert provided times into EST
        LocalDateTime startInEST = start.atZone(currZone).withZoneSameInstant(estZone).toLocalDateTime();
        LocalDateTime endInEST = end.atZone(currZone).withZoneSameInstant(estZone).toLocalDateTime();

        // Grab a Day of week from the converted time
        // This will be used to ensure that if our appointments tick over 23:59, they will register as out of business hours
        DayOfWeek startingDayOfWeek = startInEST.getDayOfWeek();
        DayOfWeek endingDayOfWeek = endInEST.getDayOfWeek();

        // Set the business hours -1 second and +1 second
        LocalTime businessStartingHour = LocalTime.of(7,59,59);
        LocalTime businessEndingHour = LocalTime.of(22, 0, 1);

        // If the appointment is after start of business but before close of business, ends on the same day it began, and is not Saturday or Sunday, return true.
        return (startInEST.toLocalTime().isAfter(businessStartingHour) && endInEST.toLocalTime().isBefore(businessEndingHour)) &&
                (endingDayOfWeek == startingDayOfWeek) && !(startingDayOfWeek == DayOfWeek.SATURDAY || startingDayOfWeek == DayOfWeek.SUNDAY);
    }

    /**
     * Check if there are any other appointments that overlap with the provided start and end times
     *
     * @param start start time of an appointment
     * @param end end time of an appointment
     * @return true if the appointment overlaps with another appointment, otherwise returns false.
     */
    public static boolean overlapsWithOtherAppointments(LocalDateTime start, LocalDateTime end) {

        for (Appointments appointment : getAllAppointments()) {
            // Check to see if start and end time overlaps with any other appointments start or end times.
            if (start.isAfter(appointment.getAppointmentStart()) || start.isBefore(appointment.getAppointmentEnd())
                    || end.isAfter(appointment.getAppointmentStart()) || end.isBefore(appointment.getAppointmentEnd())) {
                return true;
            }
        }
        return false;
    }
}
