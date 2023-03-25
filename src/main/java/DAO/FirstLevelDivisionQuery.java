package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.FirstLevelDivision;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class FirstLevelDivisionQuery {
    public static ObservableList<FirstLevelDivision> getAllFirstLevelDivisions() {
        ObservableList<FirstLevelDivision> firstLevelDivisionObservableList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM FIRST_LEVEL_DIVISIONS";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int divisionId = rs.getInt("Division_ID");
                String division = rs.getString("Division");
                int countryId = rs.getInt("Country_ID");

                FirstLevelDivision firstLevelDivision = new FirstLevelDivision(divisionId, division, countryId);

                firstLevelDivisionObservableList.add(firstLevelDivision);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return firstLevelDivisionObservableList;
    }
/*
    public static String getFirstLevelDivisionFromDivisionId(Integer divisionId) {
        try {
            //String sql = "SELECT Division FROM FIRST_LEVEL_DIVISIONS WHERE Division_ID = " + divisionId + ";";
            String sql = "SELECT Division FROM FIRST_LEVEL_DIVISIONS WHERE Division_ID = ?";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ps.setInt(1, divisionId.intValue());
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getString("Division");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public static String getCountryFromDivisionId(Integer divisionId) {
        try {
            String sql = "SELECT Country FROM COUNTRIES WHERE Country_ID = " +
                    "(SELECT Country_ID FROM first_level_divisions WHERE Division_ID = " + divisionId + ");";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getString("Country");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }
    */
}
