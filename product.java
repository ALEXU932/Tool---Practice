import java.util.List;
import java.util.ArrayList;

public class Product {
    // Attributes
    private String categor_id;
    private List<String> image;
    public List<Device> m_AUsers;
    public Insertion m_Insertion;
    public String name;
    private float price;
    private String productId;
    
    public Product() {
        this.image = new ArrayList<>();
        this.m_AUsers = new ArrayList<>();
    }

    public void addImage(String imageUrl) {
        image.add(imageUrl);
        System.out.println("Image added: " + imageUrl);
    }
    
    public void finaltext() {
        System.out.println("Finalizing product");
    }
    
    public Object getProductIntel() {
        return new Object();
    }
    
    public String productId() {
        return productId;
    }
    
    public void updateStack(int quantity) {
        System.out.println("Updating stack by: " + quantity);
        
    }
    
   
    public String getCategoryId() {
        return categor_id;
    }
    
    public void setCategoryId(String categor_id) {
        this.categor_id = categor_id;
    }
    
    public List<String> getImages() {
        return image;
    }
    
    public void setImages(List<String> image) {
        this.image = image;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public float getPrice() {
        return price;
    }
    
    public void setPrice(float price) {
        this.price = price;
    }
    
    public String getProductId() {
        return productId;
    }
    
    public void setProductId(String productId) {
        this.productId = productId;
    }
}
class Device {
    private String deviceId;
    
    public Device(String deviceId) {
        this.deviceId = deviceId;
    }
    
    public String getDeviceId() {
        return deviceId;
    }
    
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}

class Insertion {
  
    private String insertionId;
    
    public Insertion(String insertionId) {
        this.insertionId = insertionId;
    }
    
    public String getInsertionId() {
        return insertionId;
    }
    
    public void setInsertionId(String insertionId) {
        this.insertionId = insertionId;
    }
}
