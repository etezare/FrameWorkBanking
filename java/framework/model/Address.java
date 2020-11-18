package framework.model;

public class Address {
    private String street;
    private String state;
    private long zip;
    private String city;

    public Address(String street, String state, long zip, String city) {
        this.street = street;
        this.state = state;
        this.zip = zip;
        this.city = city;
    }

    public Address() {
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public long getZip() {
        return zip;
    }

    public void setZip(long zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Address{" +
            "street='" + street + '\'' +
            ", state='" + state + '\'' +
            ", zip=" + zip +
            ", city='" + city + '\'' +
            '}';
    }
}
