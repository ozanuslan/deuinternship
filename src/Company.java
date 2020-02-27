public class Company {
    private String name;
    private String address;
    private String phone;
    private String area;
    private String contactName;
    private int internCount;

    public Company(String name, String street, String no, String town, String city, String country,
            String phoneCountryCode, String phoneAreaCode, String phoneNumber, String area, String contactName) {
        this.name = name;
        this.address = street + " " + no + " " + town + " " + city + " " + country;
        this.phone = phoneCountryCode + phoneAreaCode + phoneNumber;
        this.area = area;
        this.contactName = contactName;
        this.internCount = 1;
    }

    public Company(){
        this.name = "";
    }

    public String getName(){
        return name;
    }

    public String getAddress(){
        return address;
    }

    public String getPhone(){
        return phone;
    }

    public String getArea(){
        return area;
    }

    public String getContactName(){
        return contactName;
    }

    public int getInternCount(){
        return internCount;
    }
    public void incrementInternCount() {
        internCount++;
    }
}