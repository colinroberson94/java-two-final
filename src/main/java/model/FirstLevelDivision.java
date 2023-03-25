package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import static DAO.CountryQuery.getAllCountries;
import static DAO.FirstLevelDivisionQuery.getAllFirstLevelDivisions;

public class FirstLevelDivision {
    private Integer divisionId;
    private String division;
    private Integer countryId;
    private static ObservableList<FirstLevelDivision> allFirstLevelDivision = FXCollections.observableArrayList();

    public FirstLevelDivision (Integer divisionId, String division, Integer countryId) {
        this.divisionId = divisionId;
        this.division = division;
        this.countryId = countryId;
    }

    public Integer getDivisionId() {
        return divisionId;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public String getDivision() {
        return division;
    }

    public static String getFirstLevelDivisionFromDivisionId(Integer id) {
        allFirstLevelDivision = getAllFirstLevelDivisions();

        String divisionName = allFirstLevelDivision.stream()
                .filter(fld -> fld.getDivisionId().equals(id))
                .findFirst()
                .orElse(null)
                .getDivision();

        return divisionName;
    }

    public static Integer getCountryIdFromDivisionId(Integer id) {
        allFirstLevelDivision = getAllFirstLevelDivisions();

        Integer countryId = allFirstLevelDivision.stream()
                .filter(fld -> fld.getDivisionId().equals(id))
                .findFirst()
                .orElse(null)
                .getCountryId();

        return countryId;
    }
}
