package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import static DAO.CustomerQuery.getAllCustomers;

public class Customers {
    private String customerName;
    private String customerAddress;
    private String customerPostalCode;
    private String customerPhoneNumber;
    private Integer customerId;
    private Integer divisionId;
    private String customerCountry;
    private String customerFirstLevelDivision;
    private static ObservableList<Customers> allCustomers = FXCollections.observableArrayList();


    public Customers(String customerName, String customerAddress, String customerPostalCode, String customerPhoneNumber,
                     Integer customerId, Integer divisionId, String customerCountry, String customerFirstLevelDivision) {
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPostalCode = customerPostalCode;
        this.customerPhoneNumber = customerPhoneNumber;
        this.customerId = customerId;
        this.divisionId = divisionId;
        this.customerCountry = customerCountry;
        this.customerFirstLevelDivision = customerFirstLevelDivision;
    }

    @Override
    public String toString() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public void setCustomerPostalCode(String customerPostalCode) {
        this.customerPostalCode = customerPostalCode;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public void setDivisionId(Integer divisionId) {
        this.divisionId = divisionId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public String getCustomerPostalCode() {
        return customerPostalCode;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public Integer getDivisionId() {
        return divisionId;
    }

    public String getCustomerCountry() {
        return customerCountry;
    }

    public void setCustomerCountry(String customerCountry) {
        this.customerCountry = customerCountry;
    }

    public String getCustomerFirstLevelDivision() {
        return customerFirstLevelDivision;
    }

    public void setCustomerFirstLevelDivision(String customerFirstLevelDivision) {
        this.customerFirstLevelDivision = customerFirstLevelDivision;
    }

    public static Customers getCustomerFromId(Integer id) {
        allCustomers = getAllCustomers();

        Customers customers = allCustomers.stream()
                .filter(customer -> customer.getCustomerId().equals(id))
                .findFirst()
                .orElse(null);

        return customers;
    }
}
