// =========================
// DATA SERVICE
// =========================

const DataService = {
    // Mock data storage
    data: {
        products: [],
        orders: [],
        customers: [],
        transactions: []
    },

    // Initialize with mock data
    init() {
        // Initialize with sample data
        this.data.products = [
            { id: 1, name: "Wireless Headphones", category: "Electronics", price: 299.99, stock: 45, sales: 12500 },
            { id: 2, name: "Smart Watch", category: "Electronics", price: 199.99, stock: 32, sales: 9800 },
            { id: 3, name: "Running Shoes", category: "Sports", price: 89.99, stock: 67, sales: 7200 },
            { id: 4, name: "Laptop Backpack", category: "Fashion", price: 49.99, stock: 23, sales: 5400 },
            { id: 5, name: "Bluetooth Speaker", category: "Electronics", price: 129.99, stock: 18, sales: 4500 }
        ];
        
        this.data.orders = [
            { id: "ORD-001", customer: "John Doe", email: "john@example.com", userId: "USR-001", amount: 299.99, status: "delivered", date: "2024-01-15", items: [{ name: "Wireless Headphones", quantity: 1 }] },
            { id: "ORD-002", customer: "Jane Smith", email: "jane@example.com", userId: "USR-002", amount: 149.50, status: "processing", date: "2024-01-14", items: [{ name: "Smart Watch", quantity: 1 }] },
            { id: "ORD-003", customer: "Robert Johnson", email: "robert@example.com", userId: "USR-003", amount: 89.99, status: "pending", date: "2024-01-14", items: [{ name: "Running Shoes", quantity: 1 }] },
            { id: "ORD-004", customer: "Emily Davis", email: "emily@example.com", userId: "USR-004", amount: 450.00, status: "shipped", date: "2024-01-13", items: [{ name: "Laptop Backpack", quantity: 2 }, { name: "Bluetooth Speaker", quantity: 1 }] },
            { id: "ORD-005", customer: "Michael Wilson", email: "michael@example.com", userId: "USR-005", amount: 199.99, status: "delivered", date: "2024-01-13", items: [{ name: "Smart Watch", quantity: 1 }] }
        ];
        
        this.data.customers = [
            { id: "USR-001", name: "John Doe", email: "john@example.com", orders: 5, totalSpent: 1250.50, joinDate: "2023-06-15" },
            { id: "USR-002", name: "Jane Smith", email: "jane@example.com", orders: 3, totalSpent: 899.99, joinDate: "2023-08-22" },
            { id: "USR-003", name: "Robert Johnson", email: "robert@example.com", orders: 7, totalSpent: 2100.75, joinDate: "2023-05-10" },
            { id: "USR-004", name: "Emily Davis", email: "emily@example.com", orders: 2, totalSpent: 450.00, joinDate: "2023-11-05" },
            { id: "USR-005", name: "Michael Wilson", email: "michael@example.com", orders: 4, totalSpent: 950.25, joinDate: "2023-09-18" }
        ];
        
        this.data.transactions = [
            { id: "TXN-001", type: "sale", description: "Wireless Headphones Purchase", userId: "USR-001", amount: 299.99, date: "2024-01-15", status: "completed" },
            { id: "TXN-002", type: "refund", description: "Running Shoes Refund", userId: "USR-003", amount: -89.99, date: "2024-01-14", status: "completed" },
            { id: "TXN-003", type: "sale", description: "Multiple Products Purchase", userId: "USR-004", amount: 450.00, date: "2024-01-13", status: "completed" },
            { id: "TXN-004", type: "fee", description: "Payment Gateway Fee", userId: "system", amount: -15.00, date: "2024-01-12", status: "completed" },
            { id: "TXN-005", type: "sale", description: "Smart Watch Purchase", userId: "USR-005", amount: 199.99, date: "2024-01-11", status: "completed" },
            { id: "TXN-006", type: "sale", description: "Laptop Backpack Purchase", userId: "USR-002", amount: 49.99, date: "2024-01-10", status: "completed" },
            { id: "TXN-007", type: "refund", description: "Wireless Headphones Refund", userId: "USR-001", amount: -299.99, date: "2024-01-09", status: "completed" },
            { id: "TXN-008", type: "sale", description: "Bluetooth Speaker Purchase", userId: "USR-003", amount: 129.99, date: "2024-01-08", status: "completed" }
        ];
    },

    // Getters
    getProducts() {
        return this.data.products;
    },

    getOrders() {
        return this.data.orders;
    },

    getCustomers() {
        return this.data.customers;
    },

    getTransactions() {
        return this.data.transactions;
    },

    // Data manipulation methods
    addProduct(product) {
        const newProduct = {
            ...product,
            id: this.data.products.length + 1,
            sales: 0
        };
        this.data.products.push(newProduct);
        return newProduct;
    },

    removeProduct(productId) {
        this.data.products = this.data.products.filter(p => p.id !== productId);
        return true;
    },

    updateOrderStatus(orderId, status) {
        const order = this.data.orders.find(o => o.id === orderId);
        if (order) {
            order.status = status;
            return true;
        }
        return false;
    },

    deleteOrder(orderId) {
        this.data.orders = this.data.orders.filter(o => o.id !== orderId);
        return true;
    }
};

// Initialize data service
DataService.init();

// Export for use in other files
window.DataService = DataService;