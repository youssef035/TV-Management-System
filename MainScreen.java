import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainScreen extends JFrame {

    //let's create our first panel which is the user's panel
    JPanel SubPanel;

    //the info of the user that we need
    JTextField subName;
    JTextField subLastName;
    JTextField subMobile;
    JTextField subCity;

    //let's create a label for each one
    JLabel nameLBL;
    JLabel lastLBL;
    JLabel mobileLBL;
    JLabel cityLBL;

    //panel2: subscription cycle

    JPanel SubCycle;
    //start with the text fields
    JTextField startcycleFLD;
    JTextField endcycleFLD;
    JTextField numbertvFLD;

    //a label for the date
    JLabel todayLBL;
    SimpleDateFormat df;
    Date currentDate;
    JLabel startcycleLBL;
    JLabel endcycleLBL;
    JLabel numbertvLBL;

    //panel 03 : for the packages
    JPanel packages;
    JCheckBox sportsCHKBX;
    JCheckBox moviesCHKBX;
    JCheckBox docCHKBX;

    //panel04 : package Details
    JPanel PackageDetails;
    JTextArea moviesArea;
    JTextArea sportsArea;
    JTextArea docArea;

    //panel 5 : contains check and payments
    JPanel feePanel;
    JLabel feeLBL;
    JLabel packageFeeLBL;
    JLabel totalFeeLBL;

    //panel 06 : table to display sub data
    JPanel tablePanel;
    JTable table;
    DefaultTableModel defaultTableModel;

    //panel 07 : the action panel
    JPanel actionPNL;
    //buttons on the 6th panel:
    JButton saveBTN;
    JButton loadBTN;
    JButton newBTN;

    //classes and objects
    Subscriber subscriber;
    subscription subscription;
    int packagesSelectedPrice = 0;
    int totatPrice;

    //saving
    ArrayList<subscription> listToSave = new ArrayList<>();
    File file;


    public MainScreen(){
        /**************** PANEL 01 ****************/

        SubPanel = new JPanel();

        Border panel1Title = BorderFactory.createTitledBorder("Subscriber Details");
        SubPanel.setBorder(panel1Title);
        SubPanel.setBounds(15,15,300,200);
        SubPanel.setLayout(new GridLayout(4,2));

        //the Labels
        nameLBL = new JLabel("first Name : ");
        lastLBL = new JLabel("last name : ");
        mobileLBL = new JLabel("Phone Number : ");
        cityLBL = new JLabel("City : ");

        //the text fields
        subName = new JTextField();         subName.setOpaque(false);
        subLastName =new JTextField();      subLastName.setOpaque(false);
        subMobile = new JTextField();       subMobile.setOpaque(false);
        subCity = new JTextField();         subCity.setOpaque(false);

        //adding them all to the panel1
        SubPanel.add(nameLBL);
        SubPanel.add(subName);
        SubPanel.add(lastLBL);
        SubPanel.add(subLastName);
        SubPanel.add(mobileLBL);
        SubPanel.add(subMobile);
        SubPanel.add(cityLBL);
        SubPanel.add(subCity);

        /***************** PANEL 02 *****************/
        SubCycle = new JPanel();
        Border panel02Border = BorderFactory.createTitledBorder("Cycle's details ");
        SubCycle.setBorder(panel02Border);
        SubCycle.setBounds(15,230,300,400);
        SubCycle.setLayout(new GridLayout(14,1));

        //now let's see the components of this panel

        //the label first
        todayLBL = new JLabel();
        df = new SimpleDateFormat("dd/MM/yyyy");
        currentDate = new Date();
        todayLBL.setText("today : " + df.format(currentDate));


        //start cycle date
        startcycleLBL = new JLabel("start cycle date (dd/mm/yyyy) : ");
        startcycleFLD = new JTextField();

        //end cycle date
        endcycleLBL = new JLabel("end cycle date (dd/mm/yyyy) : ");
        endcycleFLD = new JTextField();

        //number of TVs:
        numbertvLBL = new JLabel("number of TVs : ");
        numbertvFLD = new JTextField();


        //adding stuff to panel 02:
        SubCycle.add(todayLBL);
        SubCycle.add(startcycleLBL);
        SubCycle.add(startcycleFLD);
        SubCycle.add(endcycleLBL);
        SubCycle.add(endcycleFLD);
        SubCycle.add(numbertvLBL);
        SubCycle.add(numbertvFLD);

        //make opacity to the fields
        startcycleFLD.setOpaque(false);
        endcycleFLD.setOpaque(false);
        numbertvFLD.setOpaque(false);

        /******************* panel 03 *****************/
        packages = new JPanel();
        packages.setBounds(330,15,300,200);
        packages.setLayout(new GridLayout(5,1));
        Border panel03 = BorderFactory.createTitledBorder(" Available Packages");
        packages.setBorder(panel03);

        JLabel packagesLBL = new JLabel("please select your packages ");

        sportsCHKBX = new JCheckBox("Sports package");
        moviesCHKBX = new JCheckBox("Movies package");
        docCHKBX = new JCheckBox("Documentary package");

        JButton subBTN = new JButton("Submit");

        //add them to the panel 03
        packages.add(packagesLBL);
        packages.add(sportsCHKBX);
        packages.add(moviesCHKBX);
        packages.add(docCHKBX);
        packages.add(subBTN);

        //check box item listeners
        sportsCHKBX.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if(sportsCHKBX.isSelected()){
                    DisplaySportsChnels();
                    //make price changes
                }else{
                    sportsArea.setText("");
                }
            }
        });
        moviesCHKBX.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if(moviesCHKBX.isSelected()){
                    DisplayMoviesChnels();
                }else{
                    moviesArea.setText("");

                }
            }
        });
        docCHKBX.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if(docCHKBX.isSelected()){
                    DisplayDocChanels();
                }else{
                    docArea.setText("");
                }
            }
        });

        subBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    GetSubData();
                }catch (Exception ee){

                }
            }
        });


        /**************** PANEL 04 *********************/
        PackageDetails = new JPanel();
        PackageDetails.setBounds(330,230,300,400);
        PackageDetails.setLayout(new GridLayout(3,1));
        Border panel04 = BorderFactory.createTitledBorder(" Available Channels ");
        PackageDetails.setBorder(panel04);

        //the text areas. and make it un-editable for the user
        moviesArea = new JTextArea(5,1);
        moviesArea.setEditable(false);
        moviesArea.setOpaque(false);
        moviesArea.setLineWrap(true);


        sportsArea = new JTextArea(5,1);
        sportsArea.setEditable(false);
        sportsArea.setOpaque(false);
        sportsArea.setLineWrap(true);


        docArea = new JTextArea(5,1);
        docArea.setEditable(false);
        docArea.setOpaque(false);
        docArea.setLineWrap(true);

        //add them to the panel
        PackageDetails.add(moviesArea);
        PackageDetails.add(sportsArea);
        PackageDetails.add(docArea);

        /************** PANEL 05 ***************/
        //the panel
        feePanel = new JPanel();
        Border panel5 = BorderFactory.createTitledBorder("Payment");
        feePanel.setBorder(panel5);
        feePanel.setBounds(645,15,200,200);
        feePanel.setLayout(new GridLayout(3,1));

        //the label
        feeLBL = new JLabel("Installation Fee : ");
        packageFeeLBL = new JLabel("packages fee : ");
        totalFeeLBL = new JLabel("total amount to pay : ");

        //adding up to the panel
        feePanel.add(feeLBL);
        feePanel.add(packageFeeLBL);
        feePanel.add(totalFeeLBL);



        /*************** PANEL 06 *********************/
        //deal with the panel
        tablePanel = new JPanel();
        tablePanel.setBounds(645,230,515,400);
        tablePanel.setLayout(new GridLayout(3,1));
        Border border = BorderFactory.createTitledBorder("Our customers");
        tablePanel.setBorder(border);

        //deal with the table
        //step 1 : table model
        defaultTableModel = new DefaultTableModel();
        //step 2 : columns
        table = new JTable(defaultTableModel);
        defaultTableModel.addColumn("first name");
        defaultTableModel.addColumn("last name");
        defaultTableModel.addColumn("phone number");
        defaultTableModel.addColumn("start cycle");
        defaultTableModel.addColumn("end cycle");
        defaultTableModel.addColumn("total fee");
        //step 3 : always add a scroll pan when dealing with tables
        JScrollPane scrollPane = new JScrollPane(table);

        //adding to panel 6
        tablePanel.add(scrollPane);

        /*************** PANEL 07 ******************/
        actionPNL = new JPanel();
        actionPNL.setBounds(860,15,300,200);
        Border panel07 = BorderFactory.createTitledBorder("Action Tab");
        actionPNL.setBorder(panel07);
        actionPNL.setLayout(new GridLayout(3,1));

        //the buttons on panel 07
        saveBTN = new JButton("save subscription");
        loadBTN = new JButton("load subscription");
        newBTN = new JButton("new subscription");

        //add buttons to the panel 07
        actionPNL.add(saveBTN);
        actionPNL.add(loadBTN);
        actionPNL.add(newBTN);

        //add actions to the buttons
        saveBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SaveSubscriptionToDisk();
            }
        });

        loadBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<subscription> k = LoadDataFromDisk();
            }
        });

        newBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newSusbscription();
            }
        });


        //adding panels to the Jframe:
        setLayout(null);
        add(SubPanel);          //panel 01
        add(SubCycle);          //panel 02
        add(packages);          //panel 03
        add(PackageDetails);    //panel 04
        add(feePanel);          //panel 05
        add(tablePanel);        //panel 06
        add(actionPNL);         //panel 07

    }


    /*************** METHODS ****************/

    private int DisplayDocChanels() {
        //create the doc chanels

        documentary m1 = new documentary("nat geo","SP","DOC",3);
        documentary m2 = new documentary("PBS","EN","DOC",2);
        documentary m3 = new documentary("al jazeera doc","IN","DOC",3);
        documentary m4 = new documentary("Canal D","EN","DOC",4);
        documentary m5 = new documentary("historian","IT","DOC",5);
        documentary m6 = new documentary("world doc","AR","DOC",1);

        //create an arraylist
        ArrayList<documentary> documentary = new ArrayList<>();

        //add the documentaries to the documentaries array
        documentary.add(m1);
        documentary.add(m2);
        documentary.add(m3);
        documentary.add(m4);
        documentary.add(m5);
        documentary.add(m6);

        //now we need to display them
        String docChannelString = "";
        int packagePrice = 0;

        //add a loop to append them
        for(int i =0; i<documentary.size();i++){
            docChannelString +=
                    "   " + documentary.get(i).getChannelName()
                    + "   " + documentary.get(i).getLanguage()
                    + "   " + documentary.get(i).getCategory()
                    + "   " + documentary.get(i).getPrice()
                    + "\n";
            packagePrice += documentary.get(i).getPrice();
        }

        //now we need to add them to the area
        docArea.setText(docChannelString);

        return packagePrice;

    }

    private int DisplayMoviesChnels() {
        movies m1 = new movies("MBC","EN","MOVIES",5);
        movies m2 = new movies("Cinema one","EN","MOVIES",2);
        movies m3 = new movies("Cinema pro","SP","MOVIES",5);
        movies m4 = new movies("movizland","AR","MOVIES",2);
        movies m5 = new movies("film4","FR","MOVIES",1);
        movies m6 = new movies("akwam","AR","MOVIES",2);

        //create an arraylist
        ArrayList<movies> movies = new ArrayList<>();

        //add stuff to the array
        movies.add(m1);
        movies.add(m2);
        movies.add(m3);
        movies.add(m4);
        movies.add(m5);
        movies.add(m6);

        //display stuff as strings so we needed to create one
        String moviesString = "";
        int packagePrice = 0;

        //add a loop to append them
        for(int i=0;i<movies.size();i++){
            moviesString +=
                    "   " + movies.get(i).channelName +
                            "   " + movies.get(i).language +
                            "   " + movies.get(i).category +
                            "   " + movies.get(i).price
                            + "\n";
            packagePrice += movies.get(i).getPrice();
        }

        //now we have created the strong let's add it to the area
        moviesArea.setText(moviesString);

        return packagePrice;

    }
    private int DisplaySportsChnels() {
        //let's create our channels
        SportChannel m1 = new SportChannel("Arriyadia","ar","",3);
        SportChannel m2 = new SportChannel("being sports","en","",5);
        SportChannel m3 = new SportChannel("sky sports","en","",3);
        SportChannel m4 = new SportChannel("nba","en", "",2);
        SportChannel m5 = new SportChannel("wnba","en","",1);
        SportChannel m6 = new SportChannel("nfl","en","",3);

        //let's create an array to add those channels
        ArrayList<SportChannel> sportChannels = new ArrayList<>();

        //let's add the channels to the array
        sportChannels.add(m1);
        sportChannels.add(m2);
        sportChannels.add(m3);
        sportChannels.add(m4);
        sportChannels.add(m5);
        sportChannels.add(m6);

        //create a string where to display the channels
        String sportsString = "";
        int packagePrice = 0;

        //let's append them
        for(int i =0 ; i<sportChannels.size(); i++){
            sportsString +=
                    "  " + sportChannels.get(i).channelName +
                    "   " + sportChannels.get(i).language +
                    "   " + sportChannels.get(i).price
                    + "\n";
            packagePrice += sportChannels.get(i).getPrice();
        }

        //adding the string to the sports text area
        sportsArea.setText(sportsString);

        return packagePrice;

    }
    private void GetSubData() throws ParseException {
        Date currentDate = new Date();

        //subscriber date
        subscriber = new Subscriber(
                subName.getText(),
                subLastName.getText(),
                subCity.getText(),
                Integer.parseInt(subMobile.getText())
        );

        //the cycle
        Date startcycle = df.parse(startcycleFLD.getText());
        Date endcycle = df.parse(endcycleFLD.getText());

        SubscriptionCycle cycle = new SubscriptionCycle(
                df.format(startcycle),
                df.format(endcycle)
        );

        //subscription
        subscription = new subscription(
                Integer.parseInt(numbertvFLD.getText()),
                subscriber,
                cycle,
                df.format(currentDate)
        );

        feeLBL.setText("installation fee " + subscription.getTotalFee());

        showprice();
    }

    private void showprice() {

        if(docCHKBX.isSelected())
            packagesSelectedPrice += DisplayDocChanels();
        else if (moviesCHKBX.isSelected())
            packagesSelectedPrice += DisplayMoviesChnels();
        else if (sportsCHKBX.isSelected())
            packagesSelectedPrice += DisplaySportsChnels();

        packageFeeLBL.setText("packages fee : " + packagesSelectedPrice);

        totatPrice = subscription.getTotalFee() + packagesSelectedPrice;
        totalFeeLBL.setText("total fee : " + totatPrice);


    }

    private void SaveSubscriptionToDisk() {

        listToSave.add(subscription);

        file = new File("\\C:\\Users\\pc\\Desktop\\Myfile.dat");

        try{
            OutputStream os = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(os);

            //saving the list of subscriptions
            oos.writeObject(listToSave);
            oos.flush();
            oos.close();
            os.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
    private ArrayList<subscription> LoadDataFromDisk(){

        ArrayList<subscription> s = new ArrayList<>();
        file = new File("\\C:\\Users\\pc\\Desktop\\Myfile.dat");

        try {
            InputStream is = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(is);

            s = (ArrayList) ois.readObject();

            ois.close();
            is.close();


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


        //verify the list's data
        for (subscription sub : s){
            DisplaySubInTable(sub);
        }
        return s;
    }

    private void DisplaySubInTable(subscription sub) {
        //displaying data from disk into the table
        defaultTableModel.addRow(new Object[]{
                sub.getSubscriber().getFname(),
                sub.getSubscriber().getLname(),
                sub.getSubscriber().getMobile(),
                sub.getCycle().getStartDate(),
                sub.getCycle().getEndDate(),
                sub.getTotalFee()
        });

    }

    private void newSusbscription() {
        //will make all the fields empty
        subName.setText("");
        subLastName.setText("");
        subMobile.setText("");
        subCity.setText("");

        startcycleFLD.setText("");
        endcycleFLD.setText("");
        numbertvFLD.setText("");

        feeLBL.setText("installation fee : ");
        packageFeeLBL.setText("packages fee : ");
        totalFeeLBL.setText("total fee : ");

        sportsCHKBX.setSelected(false);
        moviesCHKBX.setSelected(false);
        docCHKBX.setSelected(false);

        sportsArea.setText("");
        docArea.setText("");
        moviesArea.setText("");


    }


    public static void main(String[] args){

        //let's have our main screen

        MainScreen mainScreen = new MainScreen();

        mainScreen.setBounds(250,20,1200,700);
        mainScreen.setVisible(true);
    }
}
