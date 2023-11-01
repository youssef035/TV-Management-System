import java.io.Serializable;

public class SubscriptionCycle implements Serializable {

    private String startDate;
    private String endDate;

    //constructor
    public SubscriptionCycle(String startDate, String endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    //getters and setters

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "SubscriptionCycle{" +
                "startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }
}
