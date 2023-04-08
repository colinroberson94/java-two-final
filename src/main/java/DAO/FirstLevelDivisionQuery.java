package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.FirstLevelDivision;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class FirstLevelDivisionQuery {

    /**
     * Get all First Level Divisions from the database, create corresponding objects, add them to an observable list, and then return this list.
     *
     * @return ObservableList containing all First Level Divisions in the database
     */
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
}
