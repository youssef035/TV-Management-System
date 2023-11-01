public class documentary extends TVchannels{
    int additionalFee = 12;
    public documentary(String channelName, String language, String category, int price) {
        super(channelName, language, category, price);
    }

    @Override
    public int getPrice() {

        return super.getPrice() + additionalFee;
    }
}
