package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import static DAO.CountryQuery.getAllCountries;

public class Countries {
    private Integer countryId;
    private String countryName;
    private static ObservableList<Countries> allCountries = FXCollections.observableArrayList();

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

    public static String getCountryNameFromCountryId(Integer id) {
        allCountries = getAllCountries();

        String countryName = allCountries.stream()
                .filter(country -> country.getCountryId().equals(id))
                .findFirst()
                .orElse(null)
                .getCountryName();

        return countryName;
    }
}
