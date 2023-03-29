package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contacts;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class ContactsQuery {
    public static ObservableList<Contacts> getAllContacts() {
        ObservableList<Contacts> contactsObservableList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM CONTACTS";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Integer contactId = rs.getInt("Contact_ID");
                String contactName = rs.getString("Contact_Name");
                String email = rs.getString("Email");

                Contacts contact = new Contacts(contactId, contactName, email);

                contactsObservableList.add(contact);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return contactsObservableList;
    }
}
