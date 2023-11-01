public class movies extends TVchannels{
    int additionalFee = 15;
    public movies(String channelName, String language, String category, int price) {
        super(channelName, language, category, price);
    }

    @Override
    public int getPrice() {
        return super.getPrice() + additionalFee;
    }
}
