import java.io.Serializable;

public class Subscriber implements Serializable {
    private String fname;
    private String lname;
    private String city;
    private int mobile;

    //constructor

    public Subscriber(String fname, String lname, String city, int mobile) {
        this.fname = fname;
        this.lname = lname;
        this.city = city;
        this.mobile = mobile;
    }

    //getters and setters
    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getMobile() {
        return mobile;
    }

    public void setMobile(int mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "Subscriber{" +
                "fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", city='" + city + '\'' +
                ", mobile=" + mobile +
                '}';
    }
}
