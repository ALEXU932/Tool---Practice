

import java.util.List;

/**
 * @author Tewelde
 * @version 1.0
 * @created 28-Dec-2025 6:21:08 PM
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
}//end Category