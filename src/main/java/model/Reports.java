package model;

public class Reports {
    private String type;
    private String month;
    private String name;
    private Integer total;

    public Reports(String type, int month, Integer total) {
        this.type = type;
        this.month = getMonthFromInt(month);
        this.total = total;
    }

    public Reports (String name, Integer total) {
        this.name = name;
        this.total = total;
    }

    public String getType() {
        return type;
    }

    public String getMonth() {
        return month;
    }

    public String getName() {
        return name;
    }

    public Integer getTotal() {
        return total;
    }

    private String getMonthFromInt(Integer monthInt) {
        String[] month = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

        return month[monthInt - 1];
    }
}
