import java.util.*;

public class Customer {

    private Address billingAddress;
    private String customerId;
    private Cart m_Cart;
    private Order m_Order;
    private Language preferredLanguage;
    private Address shippingAddress;

    // Constructor
    public Customer(String customerId) {
        this.customerId = customerId;
        this.m_Cart = new Cart();
        this.preferredLanguage = Language.ENGLISH;
    }

    // Add product to cart
    public void addToCart(String productId, int quantity) {
        Product product = ProductCatalog.findProductById(productId);
        if (product != null) {
            m_Cart.addProduct(product, quantity);
            System.out.println("Added " + quantity + " x " + product.name + " to cart.");
        } else {
            System.out.println("Product not found.");
        }
    }

    // Browse available products
    public List<Product> browseProduct(List<Product> products) {
        System.out.println("Browsing products...");
        for (Product p : products) {
            System.out.println(p.name + " - $" + p.price);
        }
        return products;
    }

    // Finalize customer session (e.g., checkout ready)
    public void finalize() {
        System.out.println("Customer session finalized for ID: " + customerId);
    }

    // Place order from cart
    public Order placeOrder(String paymentMethod) {
        float total = 0;

        for (Map.Entry<Product, Integer> entry : m_Cart.getItems().entrySet()) {
            total += entry.getKey().price * entry.getValue();
        }

        m_Order = new Order("ORD-" + System.currentTimeMillis(),
                            new HashMap<>(m_Cart.getItems()),
                            total);

        m_Cart.clear();

        System.out.println("Order placed using " + paymentMethod +
                           ". Total: $" + total);
        return m_Order;
    }

    // View order history (simulated)
    public List<Order> viewOrderHistory(String customerId, float minAmount, int limit) {
        List<Order> orders = OrderDatabase.getOrdersForCustomer(customerId);
        List<Order> filtered = new ArrayList<>();

        for (Order order : orders) {
            if (order.totalAmount >= minAmount && filtered.size() < limit) {
                filtered.add(order);
            }
        }

        return filtered;
    }

    // View product catalog
    public void viewProduct() {
        List<Product> products = ProductCatalog.getAllProducts();
        for (Product p : products) {
            System.out.println(p.name + " - $" + p.price);
        }
    }
}
