import java.util.List;
import java.util.ArrayList;

public class Category {
    private String categoryid;
    private boolean bActive;
    private Product np;
    public Product Product;
    private String name;
   
    public Category() {
        this.bActive = true;
    }

    public void addingneg(String parameter) {
        System.out.println("Adding negative: " + parameter);
     
    }
    
    public void finalize() {
        System.out.println("Finalizing category");
       
    }
    public int getProductCount(Product product) {
        
        return 0;
    }
    
    public Product getProductInfo() {
        return Product;
    }
    
    public List<Integer> getSubcategoryId() {
        
        return new ArrayList<>();
    }
    
    public void updateStool(int value) {
        System.out.println("Updating stool by: " + value);
   
    }
    
    
    public String getCategoryid() {
        return categoryid;
    }
    
    public void setCategoryid(String categoryid) {
        this.categoryid = categoryid;
    }
    
    public boolean isBActive() {
        return bActive;
    }
    
    public void setBActive(boolean bActive) {
        this.bActive = bActive;
    }
    
    public Product getNp() {
        return np;
    }
    
    public void setNp(Product np) {
        this.np = np;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setProduct(Product product) {
        this.Product = product;
    }
}