package model;

/**
 * Class for instantiating Reports objects.
 *
 * @author Colin Roberson
 */
public class Reports {

    /**
     * The type for the report.
     */
    private String type;

    /**
     * The month for the report.
     */
    private String month;

    /**
     * The name in the report.
     */
    private String name;

    /**
     * The totals used in the report.
     */
    private Integer total;

    /**
     * Constructor for new instances of Report objects.
     *
     * @param type The type used in the report.
     * @param month The month used in the report.
     * @param total The total used in the report.
     */
    public Reports(String type, int month, Integer total) {
        this.type = type;
        this.month = getMonthFromInt(month);
        this.total = total;
    }

    /**
     * Constructor for new instances of Report objects.
     *
     * @param name The name used in the report.
     * @param total The total used in the report.
     */
    public Reports (String name, Integer total) {
        this.name = name;
        this.total = total;
    }

    /**
     * Get report type
     *
     * @return Report type
     */
    public String getType() {
        return type;
    }

    /**
     * Get report month
     *
     * @return Report month
     */
    public String getMonth() {
        return month;
    }

    /**
     * Get report name
     *
     * @return Report name
     */
    public String getName() {
        return name;
    }

    /**
     * Get report total
     *
     * @return Report total
     */
    public Integer getTotal() {
        return total;
    }

    /**
     * Get a month name from a corresponding Integer.
     *
     * @return Month name
     */
    private String getMonthFromInt(Integer monthInt) {
        String[] month = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

        return month[monthInt - 1];
    }
}
