public class Company {
    String name;
    String address;
    String phone;
    String area;
    String contactName;
    int internCount;

    public Company(String name, String street, String no, String town, String city, String country,
            String phoneCountryCode, String phoneAreaCode, String phoneNumber, String area, String contactName) {
        this.name = name;
        this.address = "";
        this.address += street + " " + no + " " + town + " " + city + " " + country;
        this.phone = "";
        this.phone = phoneCountryCode + phoneAreaCode + phoneNumber;
        this.area = area;
        this.contactName = contactName;
        this.internCount = 1;
    }

    public void incrementInternCount() {
        internCount++;
    }
}