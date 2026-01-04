// Category.java
import java.util.List;

/**
 * @author Tewelde
 * @version 1.0
 * @created 02-Jan-2026 11:44:28 PM
 */
public class Category {

	private String categoryId;
	public boolean isActive;
	public String name;
	public Product m_Product;

	public Category(){

	}

	public void finalize() throws Throwable {

	}
	/**
	 * 
	 * @param imageUrl
	 */
	public void addImage(String imageUrl){

	}

	/**
	 * 
	 * @param product
	 */
	public int getProductCount(Product product){
		return 0;
	}

	public Product getProductInfo(){
		return null;
	}

	public List<Category> getSubcategories(){
		return null;
	}

	/**
	 * 
	 * @param quantity
	 */
	public void updateStock(int quantity){

	}
	
	// Getter and Setter methods
	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}//end Category