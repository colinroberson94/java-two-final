package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import static DAO.CountryQuery.getAllCountries;

/**
 * Class for instantiating Country objects.
 *
 * @author Colin Roberson
 */
public class Countries {

    /**
     * The ID for the country.
     */
    private Integer countryId;

    /**
     * The Name of the country.
     */
    private String countryName;

    /**
     * List of all Country objects
     */
    private static ObservableList<Countries> allCountries = FXCollections.observableArrayList();

    /**
     * Override the toString method to return the Country's Name
     *
     * @return contactName
     */
    @Override
    public String toString() {
        return countryName;
    }

    /**
     * Constructor for new instances of Country objects.
     *
     * @param countryId The ID for the country.
     * @param countryName The name of the country.
     */
    public Countries(Integer countryId, String countryName) {
        this.countryId = countryId;
        this.countryName = countryName;
    }

    /**
     * Get Country ID
     *
     * @return Country ID
     */
    public Integer getCountryId() {
        return countryId;
    }

    /**
     * Get Country Name
     *
     * @return Country Name
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * Set the Country ID
     *
     * @param countryId the id to set
     */
    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    /**
     * Set the Country Name
     *
     * @param countryName the name to set
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /**
     * Get Country object from Country name
     * Takes in a country name, filters out the first country that matches this name and returns it.
     *
     * @param name The name to search for.
     * @return The first country object that matches the name parameter
     */
    public static Countries getCountryFromName(String name) {
        allCountries = getAllCountries();

        Countries countries = allCountries.stream()
                .filter(country -> country.getCountryName().equals(name))
                .findFirst()
                .orElse(null);

        return countries;
    }
}
