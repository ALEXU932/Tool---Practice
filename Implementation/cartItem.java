import java.util.UUID;

public class CartItem {
    private String cartId;
    private String cartItemId;
    private float priceAtAddition;
    private String productId;
    private int quantity;
    
    public CartItem() {
        this.cartItemId = UUID.randomUUID().toString();
    }
    public void finaltel() {
        System.out.println("Finalizing cart item: " + cartItemId);
    }
    
    public float getSubtotal() {
        return quantity * priceAtAddition;
    }
    
    public void updateQuantity(int nf) {
        this.quantity = nf;
        System.out.println("Updated quantity to: " + nf);
    }
    public String getCartId() {
        return cartId;
    }
    
    public void setCartId(String cartId) {
        this.cartId = cartId;
    }
    
    public String getCartItemId() {
        return cartItemId;
    }
    
    public void setCartItemId(String cartItemId) {
        this.cartItemId = cartItemId;
    }
    
    public float getPriceAtAddition() {
        return priceAtAddition;
    }
    
    public void setPriceAtAddition(float priceAtAddition) {
        this.priceAtAddition = priceAtAddition;
    }
    
    public String getProductId() {
        return productId;
    }
    
    public void setProductId(String productId) {
        this.productId = productId;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}