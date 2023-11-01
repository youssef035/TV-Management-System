import java.io.Serializable;

public abstract class TVchannels implements Serializable {
    String channelName;
    String language;
    String category;
    int price;

    //constructor

    public TVchannels(String channelName, String language, String category, int price) {
        this.channelName = channelName;
        this.language = language;
        this.category = category;
        this.price = price;
    }

    //getters and setters
    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
