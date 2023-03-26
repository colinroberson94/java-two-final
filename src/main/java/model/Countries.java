package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import static DAO.CountryQuery.getAllCountries;

public class Countries {
    private Integer countryId;
    private String countryName;
    private static ObservableList<Countries> allCountries = FXCollections.observableArrayList();

    @Override
    public String toString() {
        return countryName;
    }

    public Countries(Integer countryId, String countryName) {
        this.countryId = countryId;
        this.countryName = countryName;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /**
     * Get Country Name from Country ID
     *
     * Takes in a country ID, filters out the first country that matches this ID and returns it.
     * @param id
     * @return
     */
//    public static String getCountryNameFromCountryId(Integer id) {
//        allCountries = getAllCountries();
//
//        String countryName = allCountries.stream()
//                .filter(country -> country.getCountryId().equals(id))
//                .findFirst()
//                .orElse(null)
//                .getCountryName();
//
//        return countryName;
//    }

    public static Countries getCountryFromName(String name) {
        allCountries = getAllCountries();

        Countries countries = allCountries.stream()
                .filter(country -> country.getCountryName().equals(name))
                .findFirst()
                .orElse(null);

        return countries;
    }
}
