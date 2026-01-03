

import java.util.Date;
import java.util.List;

/**
 * @author Tewelde
 * @version 1.0
 * @created 28-Dec-2025 6:21:09 PM
 */
public class SearchResult {

	private String category;
	private Date generatedAt;
	private String searchId;
	private String sortBy;
	private int totalResults;
	public AIService m_AIService;

	public SearchResult(){

	}

	public void finalize() throws Throwable {

	}
	public List<Product> getResults(){
		return null;
	}

	public int getTotalCount(){
		return 0;
	}
}//end SearchResult