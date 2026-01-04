// Analytics.java
import java.util.Date;

/**
 * @author Tewelde
 * @version 1.0
 * @created 02-Jan-2026 11:44:28 PM
 */
public class Analytics {

	private JSON data;
	private String format;
	private Date generatedDate;
	private Date periodEnd;
	private Date periodStart;
	private String reportId;
	private String reportType;
	public Order m_Order;

	public Analytics(){

	}

	public void finalize() throws Throwable {

	}
	/**
	 * 
	 * @param format
	 */
	public void exportReport(String format){

	}

	/**
	 * 
	 * @param startDate
	 * @param endDate
	 */
	public SalesReport generateSalesReport(Date startDate, Date endDate){
		return null;
	}
	
	// Getter and Setter methods
	public JSON getData() {
		return data;
	}

	public void setData(JSON data) {
		this.data = data;
	}

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

	public Date getPeriodEnd() {
		return periodEnd;
	}

	public void setPeriodEnd(Date periodEnd) {
		this.periodEnd = periodEnd;
	}

	public Date getPeriodStart() {
		return periodStart;
	}

	public void setPeriodStart(Date periodStart) {
		this.periodStart = periodStart;
	}

	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
}//end Analytics