import java.util.Date;

/**
 * @author Tewelde
 * @version 1.0
 * @created 02-Jan-2026 11:44:28 PM
 */
public class SalesReport {

	private String format;
	private Date generatedDate;
	private Date generatedTime;
	private String reportId;
	private String reporttype;
	private int totalCustomers;
	private int totalOrders;
	private float totalRevenue;
	public Analytics m_Analytics;

	public SalesReport(){

	}

	public void finalize() throws Throwable {

	}
	
	// Getter and Setter methods
	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public Date getGeneratedDate() {
		return generatedDate;
	}

	public void setGeneratedDate(Date generatedDate) {
		this.generatedDate = generatedDate;
	}

	public Date getGeneratedTime() {
		return generatedTime;
	}

}//end SalesReport