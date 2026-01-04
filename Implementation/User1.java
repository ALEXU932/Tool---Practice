import java.util.List;
import java.util.ArrayList;

public class User {
    // Attributes from class diagram
    protected String email;
    private String passwordHash;
    private String phoneNumber;
    private String role; // Using String instead of Permission enum for simplicity
    private String userId;
    public String userName; // Public as per diagram
    
    // Composition relationships
    public Address shippingAddress;
    public Address billingAddress;
    
    // Constructors
    public User() {
        this.shippingAddress = new Address();
        this.billingAddress = new Address();
    }
    
    public User(String userId, String userName, String email) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.shippingAddress = new Address();
        this.billingAddress = new Address();
    }
    
    // Getter and Setter methods
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getSendName() { // Typo in diagram? Should be getUserName?
        return userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    // Business methods
    public boolean login(String email, String password) {
        // Basic login logic - in real system, compare hashed passwords
        if (this.email.equals(email) && this.passwordHash != null) {
            // In real app, you would hash the input password and compare
            // For simplicity, assuming password verification
            return true;
        }
        return false;
    }
    
    public void updateProfile() {
        // Logic to update user profile
        System.out.println("Updating profile for user: " + userName);
        // Implementation would save changes to database
    }
    
    // Getters and setters for other attributes
    public String getPasswordHash() {
        return passwordHash;
    }
    
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public String getRole() {
        return role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
    
    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    // Static main method as per diagram
    public static void main(String[] args) {
        System.out.println("User class executed");
    }
    
    @Override
    protected void finalize() throws Throwable {
        // Cleanup code
        super.finalize();
    }
}