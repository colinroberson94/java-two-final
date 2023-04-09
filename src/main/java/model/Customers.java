package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import static DAO.CustomerQuery.getAllCustomers;

/**
 * Class for instantiating Customer objects.
 *
 * @author Colin Roberson
 */
public class Customers {

    /**
     * The name of the customer.
     */
    private String customerName;

    /**
     * The address of the customer.
     */
    private String customerAddress;

    /**
     * The postal code of the customer.
     */
    private String customerPostalCode;

    /**
     * The phone number of the customer.
     */
    private String customerPhoneNumber;

    /**
     * The ID of the customer.
     */
    private Integer customerId;

    /**
     * The First Level Division ID of the customer.
     */
    private Integer divisionId;

    /**
     * The country of the customer.
     */
    private String customerCountry;

    /**
     * The First Level Division of the customer.
     */
    private String customerFirstLevelDivision;

    /**
     * List of all customer objects
     */
    private static ObservableList<Customers> allCustomers = FXCollections.observableArrayList();

    /**
     * Constructor for new instances of Customer objects.
     *
     * @param customerName The name of the customer.
     * @param customerAddress The address of the customer.
     * @param customerPostalCode The postal code of the customer.
     * @param customerPhoneNumber The phone number of the customer.
     * @param customerId The ID of the customer.
     * @param divisionId The First Level Division ID of the customer.
     * @param customerCountry The country of the customer.
     * @param customerFirstLevelDivision The First Level Division of the customer.
     */
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

    /**
     * Override the toString method to return the Customer's Name
     *
     * @return contactName
     */
    @Override
    public String toString() {
        return customerName;
    }

    /**
     * Set the customer name
     *
     * @param customerName the customer name to set
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * Set the customer address
     *
     * @param customerAddress the customer address to set
     */
    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    /**
     * Set the customer's postal code
     *
     * @param customerPostalCode the customer postal code to set
     */
    public void setCustomerPostalCode(String customerPostalCode) {
        this.customerPostalCode = customerPostalCode;
    }

    /**
     * Set the customer's phone number
     *
     * @param customerPhoneNumber the customer phone number to set
     */
    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    /**
     * Set the customer ID
     *
     * @param customerId the customer ID to set
     */
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    /**
     * Set the First Level Division ID
     *
     * @param divisionId the First level division ID to set
     */
    public void setDivisionId(Integer divisionId) {
        this.divisionId = divisionId;
    }

    /**
     * Set the customer's country
     *
     * @param customerCountry the customer country to set
     */
    public void setCustomerCountry(String customerCountry) {
        this.customerCountry = customerCountry;
    }

    /**
     * Set the customer's First level Division
     *
     * @param customerFirstLevelDivision the customer First level division to set
     */
    public void setCustomerFirstLevelDivision(String customerFirstLevelDivision) {
        this.customerFirstLevelDivision = customerFirstLevelDivision;
    }

    /**
     * Get Customer name
     *
     * @return Customer name
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Get Customer Address
     *
     * @return Customer Address
     */
    public String getCustomerAddress() {
        return customerAddress;
    }

    /**
     * Get Customer Postal Code
     *
     * @return Customer Postal Code
     */
    public String getCustomerPostalCode() {
        return customerPostalCode;
    }

    /**
     * Get Customer phone number
     *
     * @return Customer phone number
     */
    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    /**
     * Get Customer ID
     *
     * @return Customer ID
     */
    public Integer getCustomerId() {
        return customerId;
    }

    /**
     * Get First Level Division ID
     *
     * @return First Level Division ID
     */
    public Integer getDivisionId() {
        return divisionId;
    }

    /**
     * Get Customer Country
     *
     * @return customer country
     */
    public String getCustomerCountry() {
        return customerCountry;
    }

    /**
     * Get first level division
     *
     * @return first level division
     */
    public String getCustomerFirstLevelDivision() {
        return customerFirstLevelDivision;
    }

    /**
     * Take in a customer ID and return a corresponding customer object
     *
     * A lambda is chosen here instead of a loop as the findFirst method will find the first occurrence and then stop.
     * This prevents the need to loop through all customer objects in the ObservableList.
     *
     * @param id customer ID to search for
     * @return the customer object which corresponds with the provided ID
     */
    public static Customers getCustomerFromId(Integer id) {
        allCustomers = getAllCustomers();

        Customers customers = allCustomers.stream()
                .filter(customer -> customer.getCustomerId().equals(id))
                .findFirst()
                .orElse(null);

        return customers;
    }
}
