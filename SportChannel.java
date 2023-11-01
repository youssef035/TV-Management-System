public class SportChannel extends TVchannels{
    int additionalFee = 10;
    public SportChannel(String channelName, String language, String category, int price) {
        super(channelName, language, category, price);
    }

    @Override
    public int getPrice() {
        return super.getPrice() + additionalFee;
    }
}
