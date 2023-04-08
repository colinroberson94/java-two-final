package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Countries;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class CountryQuery {

    /**
     * Get all countries from the database, create corresponding objects, add them to an observable list, and then return this list.
     *
     * @return ObservableList containing all countries in the database
     */
    public static ObservableList<Countries> getAllCountries() {
        ObservableList<Countries> countriesObservableList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM COUNTRIES";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int countryId = rs.getInt("Country_ID");
                String countryName = rs.getString("Country");

                Countries country = new Countries(countryId, countryName);

                countriesObservableList.add(country);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return countriesObservableList;
    }
}
