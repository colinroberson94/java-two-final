package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import static DAO.FirstLevelDivisionQuery.getAllFirstLevelDivisions;

/**
 * Class for instantiating First Level Division objects.
 *
 * @author Colin Roberson
 */
public class FirstLevelDivision {

    /**
     * The ID for the first level division.
     */
    private Integer divisionId;

    /**
     * The name of the division.
     */
    private String division;

    /**
     * The country ID associated with the first level division.
     */
    private Integer countryId;

    /**
     * List of all First Level Division objects
     */
    private static ObservableList<FirstLevelDivision> allFirstLevelDivision = FXCollections.observableArrayList();

    /**
     * Override the toString method to return the First Level Division's Name
     *
     * @return division name
     */
    @Override
    public String toString() {
        return division;
    }

    /**
     * Constructor for new instances of First Level Division objects.
     *
     * @param divisionId The ID for the First Level Division.
     * @param division The name of the First Level Division.
     * @param countryId The country ID associated with the First Level Division.
     */
    public FirstLevelDivision (Integer divisionId, String division, Integer countryId) {
        this.divisionId = divisionId;
        this.division = division;
        this.countryId = countryId;
    }

    /**
     * Get Division ID
     *
     * @return Division ID
     */
    public Integer getDivisionId() {
        return divisionId;
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
     * Get Division Name
     *
     * @return Division Name
     */
    public String getDivision() {
        return division;
    }

    /**
     * Get list of first level divisions with a specific country ID
     * Takes in a country ID, filters out First Level Divisions that match this ID, adds these to a list, and then returns this list
     *
     * @param countryId Country ID to search for
     * @return Observable List containing first level divisions with a specific country ID
     */
    public static ObservableList<FirstLevelDivision> getFirstLevelDivisionOfCountry(Integer countryId) {
        ObservableList<FirstLevelDivision> firstLevelDivisionObservableList = FXCollections.observableArrayList();

        getAllFirstLevelDivisions().forEach(fld -> {
            if (fld.getCountryId().equals(countryId)) {
                firstLevelDivisionObservableList.add(fld);
            }
        });

        return firstLevelDivisionObservableList;
    }

    /**
     * Get first level division object by name.
     * Takes in a first level division name and then returns a corresponding object.
     *
     * @param name first level division to search for
     * @return First level division object that corresponds with the parameter.
     */
    public static FirstLevelDivision getFirstLevelDivisionFromName(String name) {
        allFirstLevelDivision = getAllFirstLevelDivisions();

        FirstLevelDivision firstLevelDivision = allFirstLevelDivision.stream()
                .filter(fld -> fld.getDivision().equals(name))
                .findFirst()
                .orElse(null);

        return firstLevelDivision;
    }
}
