package model;

public class Reports {
    private String type;
    private String month;
    private String name;
    private Integer total;

    public Reports(String type, String month, Integer total) {
        this.type = type;
        this.month = month;
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

    public void setType(String type) {
        this.type = type;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
