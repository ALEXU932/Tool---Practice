import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Analytics {

    private String data;
    private String format;
    private Date generatedDate;
    private Order m_Order;
    private Date periodEnd;
    private Date periodStart;
    private String reportId;
    private String reportType;

    public Analytics() {
        this.generatedDate = new Date();
    }

    // Export report in specified format
    public void exportReport(String format) {
        this.format = format;
        System.out.println("Exporting report [" + reportId + "] in " + format + " format...");
        System.out.println(data);
    }

    // Finalize report (lock data, set generated date)
    public void finalize() {
        this.generatedDate = new Date();
        System.out.println("Report finalized on: " + generatedDate);
    }

    // Generate sales report for a given period
    public SalesReport generateSalesReport(Date start, Date end) {
        this.periodStart = start;
        this.periodEnd = end;
        this.reportType = "Sales";
        this.reportId = "SR-" + System.currentTimeMillis();

        // Simulated order data
        List<Order> orders = getOrdersBetween(start, end);

        double totalSales = 0;
        for (Order order : orders) {
            totalSales += order.getTotalAmount();
        }

        SalesReport report = new SalesReport(start, end, totalSales);
        this.data = report.toString();

        System.out.println("Sales report generated: " + reportId);
        return report;
    }

    // Simulated data source
    private List<Order> getOrdersBetween(Date start, Date end) {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order(120.50, start));
        orders.add(new Order(300.00, end));
        orders.add(new Order(79.99, new Date()));
        return orders;
    }
}
