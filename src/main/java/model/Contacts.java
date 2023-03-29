package model;

public class Contacts {
    private String contactName;
    private String email;
    private Integer contactId;

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

}
