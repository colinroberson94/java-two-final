package model;

import DAO.FirstLevelDivisionQuery;

public class Customers {
    private String customerName;
    private String customerAddress;
    private String customerPostalCode;
    private String customerPhoneNumber;
    private Integer customerId;
    private Integer divisionId;
    private String customerCountry;
    private String customerFirstLevelDivision;

    public Customers(String customerName, String customerAddress, String customerPostalCode, String customerPhoneNumber,
                     Integer customerId, Integer divisionId) {
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPostalCode = customerPostalCode;
        this.customerPhoneNumber = customerPhoneNumber;
        this.customerId = customerId;
        this.divisionId = divisionId;
        customerCountry = Countries.getCountryNameFromCountryId(
                                    FirstLevelDivision.getCountryIdFromDivisionId(divisionId));
        customerFirstLevelDivision = FirstLevelDivision.getFirstLevelDivisionFromDivisionId(divisionId);
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
}
