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

    @Override
    public String toString() {
        return division;
    }

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

    /**
     * Get First Level Division name from First Level Division ID
     *
     * Takes in a first level division ID, filters out the first ID that matches the provided ID and returns the corresponding name.
     * @param id
     * @return
     */
    public static String getFirstLevelDivisionFromDivisionId(Integer id) {
        allFirstLevelDivision = getAllFirstLevelDivisions();

        String divisionName = allFirstLevelDivision.stream()
                .filter(fld -> fld.getDivisionId().equals(id))
                .findFirst()
                .orElse(null)
                .getDivision();

        return divisionName;
    }

    /**
     * Get Country ID from First Level Division ID
     *
     * Takes in a first level division ID, filters out the first ID that matches the provided ID and returns the country ID.
     * @param id
     * @return
     */
    public static Integer getCountryIdFromDivisionId(Integer id) {
        allFirstLevelDivision = getAllFirstLevelDivisions();

        Integer countryId = allFirstLevelDivision.stream()
                .filter(fld -> fld.getDivisionId().equals(id))
                .findFirst()
                .orElse(null)
                .getCountryId();

        return countryId;
    }

    public static FirstLevelDivision getFirstLevelDivisionFromName(String name) {
        allFirstLevelDivision = getAllFirstLevelDivisions();

        FirstLevelDivision firstLevelDivision = allFirstLevelDivision.stream()
                .filter(fld -> fld.getDivision().equals(name))
                .findFirst()
                .orElse(null);

        return firstLevelDivision;
    }
}
