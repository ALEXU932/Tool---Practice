public class Address {
    private String addressId;
    private String city;
    private String country;
    private String zipCode;
    
    // Constructors
    public Address() {
    }
    
    public Address(String addressId, String city, String country, String zipCode) {
        this.addressId = addressId;
        this.city = city;
        this.country = country;
        this.zipCode = zipCode;
    }
    
    // Getter and Setter methods
    public String getAddressId() {
        return addressId;
    }
    
    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }
    
    public String getCity() {
        return city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    public String getCountry() {
        return country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
    
    public String getZipCode() {
        return zipCode;
    }
    
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
    
    // Validation method
    public boolean validate() {
        // Basic validation logic
        if (city == null || city.trim().isEmpty()) {
            return false;
        }
        if (country == null || country.trim().isEmpty()) {
            return false;
        }
        if (zipCode == null || zipCode.trim().isEmpty()) {
            return false;
        }
        // Add more validation as needed
        return true;
    }
    //this is modifided
    @Override
    protected void finalize() throws Throwable {
        // Cleanup code if needed
        super.finalize();
    }
    
    @Override
    public String toString() {
        return "Address{" +
                "addressId='" + addressId + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }
}