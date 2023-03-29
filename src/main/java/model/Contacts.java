package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import static DAO.ContactsQuery.getAllContacts;

public class Contacts {
    private String contactName;
    private String email;
    private Integer contactId;
    private static ObservableList<Contacts> allContacts = FXCollections.observableArrayList();


    public Contacts (Integer contactId, String contactName, String email) {
        this.contactName = contactName;
        this.email = email;
        this.contactId = contactId;
    }

    public String getContactName() {
        return contactName;
    }

    public String getEmail() {
        return email;
    }

    public Integer getContactId() {
        return contactId;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }

    @Override
    public String toString() {
        return contactName;
    }

    public static Contacts getContactFromId(Integer id) {
        allContacts = getAllContacts();

        Contacts contacts = allContacts.stream()
                .filter(contact -> contact.getContactId().equals(id))
                .findFirst()
                .orElse(null);

        return contacts;
    }

}
