import java.io.Serializable;

public class subscription implements Serializable {

    private int installFee;     //fixed fee ==> 10 per TV
    private int nbTV;
    private Subscriber subscriber;
    private SubscriptionCycle cycle;
    private String datee;

    //total fee
    private int totalFee;

    public subscription(int nbTV, Subscriber subscriber, SubscriptionCycle cycle, String datee) {
        this.nbTV = nbTV;
        this.subscriber = subscriber;
        this.cycle = cycle;
        this.datee = datee;

        this.installFee = nbTV * 10;
    }

    //getters and setters

    public int getNbTV() {
        return nbTV;
    }

    public void setNbTV(int nbTV) {
        this.nbTV = nbTV;
    }

    public Subscriber getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
    }

    public SubscriptionCycle getCycle() {
        return cycle;
    }

    public void setCycle(SubscriptionCycle cycle) {
        this.cycle = cycle;
    }

    public String getDatee() {
        return datee;
    }

    public void setDatee(String datee) {
        this.datee = datee;
    }

    public int getTotalFee() {
        totalFee = installFee + 5;
        return totalFee;
    }


    @Override
    public String toString() {
        return "subscription{" +
                "installFee=" + installFee +
                ", nbTV=" + nbTV +
                ", subscriber=" + subscriber +
                ", cycle=" + cycle +
                ", datee='" + datee + '\'' +
                ", totalFee=" + totalFee +
                '}';
    }
}
