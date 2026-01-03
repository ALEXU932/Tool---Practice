import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class Cart {
    private String cartid;
    private Date contabact;
    private List<CartItem> items;
    public CartItem m_CartItem;
    public Product m_Product;
    public String userId;
   
    public Cart() {
        this.items = new ArrayList<>();
        this.contabact = new Date();
    }
    private void addproduct(String param1, String param2) {
        System.out.println("Adding product: " + param1 + ", " + param2);
    
    public float calculateTotal() {
        float total = 0.0f;
        return total;
    }
    
    public void finaltext() {
        System.out.println("Finalizing cart");
    }
    public String getCartId() {
        return cartid;
    }
    
    public Date getCreatedAt() {
        return contabact;
    }
    
    public List<CartItem> getItem() {
        return items;
    }
    
    public String getUserId() {
        return userId;
    }
    public void removeItem(String itemId) {
        System.out.println("Removing item: " + itemId);
    }
    
    public void setCartId(String cartid) {
        this.cartid = cartid;
    }
    
    public void setCreateAt(String dateString) {
        System.out.println("Setting create date: " + dateString);
    }
    
    public void setItem(List<CartItem> items) {
        this.items = items;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
}