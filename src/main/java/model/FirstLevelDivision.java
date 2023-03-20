package model;

public class FirstLevelDivision {
    private Integer divisionId;
    private String division;
    private Integer countryId;

    public FirstLevelDivision (Integer divisionId, String division, Integer countryId) {
        this.divisionId = divisionId;
        this.division = division;
        this.countryId = countryId;
    }
}
