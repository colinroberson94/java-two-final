package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import static DAO.ContactsQuery.getAllContacts;

/**
 * Class for instantiating Contact objects.
 *
 * @author Colin Roberson
 */
public class Contacts {
    /**
     * The name of the contact
     */
    private String contactName;

    /**
     * The email of the contact
     */
    private String email;

    /**
     * The contactID of the contact
     */
    private Integer contactId;

    /**
     * List of all Contact objects
     */
    private static ObservableList<Contacts> allContacts = FXCollections.observableArrayList();

    /**
     * Constructor for new instances of Contact objects.
     *
     * @param contactId The ID for the contact.
     * @param contactName The name of the contact.
     * @param email The email of the contact.
     */
    public Contacts (Integer contactId, String contactName, String email) {
        this.contactName = contactName;
        this.email = email;
        this.contactId = contactId;
    }

    /**
     * Get Contact Name
     *
     * @return Contact Name
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * Get Contact email
     *
     * @return Contact email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Get Contact ID
     *
     * @return Contact ID
     */
    public Integer getContactId() {
        return contactId;
    }

    /**
     * Set the Contact Name
     *
     * @param contactName the name to set
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * Set the Contact Email
     *
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Set the Contact ID
     *
     * @param contactId the ID to set
     */
    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }

    /**
     * Override the toString method to return the Contact's Name
     *
     * @return contactName
     */
    @Override
    public String toString() {
        return contactName;
    }

    /**
     * Get the contact object which contains a specific ID
     * Takes in a contact ID, filters out the first contact that matches this ID and returns it.
     *
     * @param id the ID of the contact which will be returned
     * @return Contact which matches the provided ID
     */
    public static Contacts getContactFromId(Integer id) {
        allContacts = getAllContacts();

        Contacts contacts = allContacts.stream()
                .filter(contact -> contact.getContactId().equals(id))
                .findFirst()
                .orElse(null);

        return contacts;
    }

}
