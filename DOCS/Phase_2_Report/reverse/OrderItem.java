
 // OrderItem.java
/**
 * @author Tewelde
 * @version 1.0
 * @created 02-Jan-2026 11:44:28 PM
 */
public class OrderItem {

	private String orderId;
	private String orderItemId;
	private String productId;
	private int quantity;
	private float subtotal;

	public OrderItem(){

	}

	public void finalize() throws Throwable {

	}
	public float calculateSubtotal(){
		return subtotal;
	}

	public Order getOrderDetails(){
		return null;
	}

	public Product getProductInfo(){
		return null;
	}
	
	// Getter and Setter methods
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(String orderItemId) {
		this.orderItemId = orderItemId;
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

	public float getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(float subtotal) {
		this.subtotal = subtotal;
	}
}//end OrderItem
public class OrderItem {

	private String orderId;
	private String orderItemId;
	private String productId;
	private int quantity;
	private float subtotal;

	public OrderItem(){

	}

	public void finalize() throws Throwable {

	}
	public float calculateSubtotal(){
		return subtotal;
	}

	public Order getOrderDetails(){
		return null;
	}

	public Product getProductInfo(){
		return null;
	}
	
	// Getter and Setter methods
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(String orderItemId) {
		this.orderItemId = orderItemId;
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

	public float getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(float subtotal) {
		this.subtotal = subtotal;
	}
}//end OrderItem