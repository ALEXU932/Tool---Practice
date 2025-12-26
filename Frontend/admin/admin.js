// =========================
// DOM ELEMENTS & STATE
// =========================
const mainContainer = document.querySelector('.mainContainer');
const sidebarButtons = document.querySelectorAll('.adminTask');
const mainBody = document.querySelector('.mainbody');
const sidebarToggle = document.querySelector('.sidebar-toggle');
const themeToggle = document.querySelector('.brightness');
let currentView = 'dashboard';

// Mock data for demonstration - UPDATED WITH USER IDs
const mockData = {
    products: [
        { id: 1, name: "Wireless Headphones", category: "Electronics", price: 299.99, stock: 45, sales: 12500 },
        { id: 2, name: "Smart Watch", category: "Electronics", price: 199.99, stock: 32, sales: 9800 },
        { id: 3, name: "Running Shoes", category: "Sports", price: 89.99, stock: 67, sales: 7200 },
        { id: 4, name: "Laptop Backpack", category: "Fashion", price: 49.99, stock: 23, sales: 5400 },
        { id: 5, name: "Bluetooth Speaker", category: "Electronics", price: 129.99, stock: 18, sales: 4500 }
    ],
    orders: [
        { id: "ORD-001", customer: "John Doe", email: "john@example.com", userId: "USR-001", amount: 299.99, status: "delivered", date: "2024-01-15", items: [{ name: "Wireless Headphones", quantity: 1 }] },
        { id: "ORD-002", customer: "Jane Smith", email: "jane@example.com", userId: "USR-002", amount: 149.50, status: "processing", date: "2024-01-14", items: [{ name: "Smart Watch", quantity: 1 }] },
        { id: "ORD-003", customer: "Robert Johnson", email: "robert@example.com", userId: "USR-003", amount: 89.99, status: "pending", date: "2024-01-14", items: [{ name: "Running Shoes", quantity: 1 }] },
        { id: "ORD-004", customer: "Emily Davis", email: "emily@example.com", userId: "USR-004", amount: 450.00, status: "shipped", date: "2024-01-13", items: [{ name: "Laptop Backpack", quantity: 2 }, { name: "Bluetooth Speaker", quantity: 1 }] },
        { id: "ORD-005", customer: "Michael Wilson", email: "michael@example.com", userId: "USR-005", amount: 199.99, status: "delivered", date: "2024-01-13", items: [{ name: "Smart Watch", quantity: 1 }] }
    ],
    customers: [
        { id: "USR-001", name: "John Doe", email: "john@example.com", orders: 5, totalSpent: 1250.50, joinDate: "2023-06-15" },
        { id: "USR-002", name: "Jane Smith", email: "jane@example.com", orders: 3, totalSpent: 899.99, joinDate: "2023-08-22" },
        { id: "USR-003", name: "Robert Johnson", email: "robert@example.com", orders: 7, totalSpent: 2100.75, joinDate: "2023-05-10" },
        { id: "USR-004", name: "Emily Davis", email: "emily@example.com", orders: 2, totalSpent: 450.00, joinDate: "2023-11-05" },
        { id: "USR-005", name: "Michael Wilson", email: "michael@example.com", orders: 4, totalSpent: 950.25, joinDate: "2023-09-18" }
    ],
    transactions: [
        { id: "TXN-001", type: "sale", description: "Wireless Headphones Purchase", userId: "USR-001", amount: 299.99, date: "2024-01-15", status: "completed" },
        { id: "TXN-002", type: "refund", description: "Running Shoes Refund", userId: "USR-003", amount: -89.99, date: "2024-01-14", status: "completed" },
        { id: "TXN-003", type: "sale", description: "Multiple Products Purchase", userId: "USR-004", amount: 450.00, date: "2024-01-13", status: "completed" },
        { id: "TXN-004", type: "fee", description: "Payment Gateway Fee", userId: "system", amount: -15.00, date: "2024-01-12", status: "completed" },
        { id: "TXN-005", type: "sale", description: "Smart Watch Purchase", userId: "USR-005", amount: 199.99, date: "2024-01-11", status: "completed" },
        { id: "TXN-006", type: "sale", description: "Laptop Backpack Purchase", userId: "USR-002", amount: 49.99, date: "2024-01-10", status: "completed" },
        { id: "TXN-007", type: "refund", description: "Wireless Headphones Refund", userId: "USR-001", amount: -299.99, date: "2024-01-09", status: "completed" },
        { id: "TXN-008", type: "sale", description: "Bluetooth Speaker Purchase", userId: "USR-003", amount: 129.99, date: "2024-01-08", status: "completed" }
    ]
};

// =========================
// INITIALIZATION
// =========================
document.addEventListener('DOMContentLoaded', () => {
    // Check if sidebar should be hidden
    const sidebarHidden = localStorage.getItem('sidebarHidden') === 'true';
    if (sidebarHidden) {
        mainBody.classList.add('sidebar-hidden');
    }
    
    // Check theme preference
    const theme = localStorage.getItem('theme') || 'light';
    if (theme === 'dark') {
        document.documentElement.classList.add('dark-theme');
        themeToggle.classList.add('active');
        themeToggle.innerHTML = '<i class="fa-solid fa-moon"></i>';
    }
    
    // Set active state for dashboard button
    setActiveButton('dashboard');
    
    // Load initial dashboard view
    loadDashboard();
    
    // Add event listeners to sidebar buttons
    sidebarButtons.forEach(button => {
        const buttonId = button.id || button.querySelector('span').textContent.toLowerCase().replace(' ', '');
        button.addEventListener('click', () => handleSidebarClick(buttonId));
    });
    
    // Setup sidebar toggle
    if (sidebarToggle) {
        sidebarToggle.addEventListener('click', toggleSidebar);
    }
    
    // Setup theme toggle
    if (themeToggle) {
        themeToggle.addEventListener('click', toggleTheme);
    }
});

// =========================
// SIDEBAR TOGGLE FUNCTION
// =========================
function toggleSidebar() {
    mainBody.classList.toggle('sidebar-hidden');
    
    // Save preference to localStorage
    const isHidden = mainBody.classList.contains('sidebar-hidden');
    localStorage.setItem('sidebarHidden', isHidden.toString());
    
    // Update toggle button icon
    const icon = sidebarToggle.querySelector('i');
    if (isHidden) {
        icon.className = 'fa-solid fa-bars';
        sidebarToggle.title = 'Show Sidebar';
    } else {
        icon.className = 'fa-solid fa-times';
        sidebarToggle.title = 'Hide Sidebar';
    }
}

// =========================
// THEME TOGGLE FUNCTION
// =========================
function toggleTheme() {
    const html = document.documentElement;
    const isDark = html.classList.toggle('dark-theme');
    
    // Update button state and icon
    themeToggle.classList.toggle('active');
    if (isDark) {
        themeToggle.innerHTML = '<i class="fa-solid fa-moon"></i>';
        themeToggle.title = 'Switch to Light Mode';
    } else {
        themeToggle.innerHTML = '<i class="fa-regular fa-sun"></i>';
        themeToggle.title = 'Switch to Dark Mode';
    }
    
    // Save theme preference
    localStorage.setItem('theme', isDark ? 'dark' : 'light');
}

// =========================
// SIDEBAR HANDLER
// =========================
function handleSidebarClick(view) {
    setActiveButton(view);
    currentView = view;
    
    switch(view) {
        case 'dashboard':
            loadDashboard();
            break;
        case 'orders':
            loadOrders();
            break;
        case 'carts':
            loadCarts();
            break;
        case 'addproduct':
            loadAddProduct();
            break;
        case 'customers':
            loadCustomers();
            break;
        case 'removeproduct':
            loadRemoveProduct();
            break;
        case 'transaction':
            loadTransactionLog();
            break;
        case 'settings':
            loadSettings();
            break;
        default:
            loadDashboard();
    }
}

function setActiveButton(buttonId) {
    sidebarButtons.forEach(button => {
        const btnId = button.id || button.querySelector('span').textContent.toLowerCase().replace(' ', '');
        if (btnId === buttonId) {
            button.classList.add('active');
        } else {
            button.classList.remove('active');
        }
    });
}

// =========================
// DASHBOARD VIEW
// =========================
function loadDashboard() {
    mainContainer.innerHTML = `
        <div class="dashboard">
            <div class="dashboard-header">
                <h1><i class="fa-solid fa-grip"></i> Dashboard Overview</h1>
                <div class="stats-summary">
                    <div class="stat-card">
                        <i class="fa-solid fa-shopping-cart"></i>
                        <h3>Total Orders</h3>
                        <p class="stat-value">${mockData.orders.length}</p>
                    </div>
                    <div class="stat-card">
                        <i class="fa-solid fa-dollar-sign"></i>
                        <h3>Total Revenue</h3>
                        <p class="stat-value">$${mockData.orders.reduce((sum, order) => sum + order.amount, 0).toFixed(2)}</p>
                    </div>
                    <div class="stat-card">
                        <i class="fa-solid fa-users"></i>
                        <h3>Total Customers</h3>
                        <p class="stat-value">${mockData.customers.length}</p>
                    </div>
                    <div class="stat-card">
                        <i class="fa-solid fa-box"></i>
                        <h3>Total Products</h3>
                        <p class="stat-value">${mockData.products.length}</p>
                    </div>
                </div>
            </div>

            <div class="dashboard-charts">
                <div class="chart-container">
                    <h2><i class="fa-solid fa-chart-line"></i> Sales Overview</h2>
                    <canvas id="salesChart"></canvas>
                </div>
                <div class="chart-container">
                    <h2><i class="fa-solid fa-chart-pie"></i> Sales by Category</h2>
                    <canvas id="categoryChart"></canvas>
                </div>
            </div>

            <div class="dashboard-tables">
                <div class="table-container">
                    <h2><i class="fa-solid fa-clock-rotate-left"></i> Recent Orders</h2>
                    <div class="table-wrapper">
                        <table>
                            <thead>
                                <tr>
                                    <th>Order ID</th>
                                    <th>Customer</th>
                                    <th>User ID</th>
                                    <th>Amount</th>
                                    <th>Status</th>
                                    <th>Date</th>
                                </tr>
                            </thead>
                            <tbody id="ordersTableBody">
                            </tbody>
                        </table>
                    </div>
                </div>

                <div class="table-container">
                    <h2><i class="fa-solid fa-star"></i> Top Products</h2>
                    <div class="top-products-list" id="topProductsList">
                    </div>
                </div>
            </div>
        </div>
    `;

    // Load dashboard data
    loadRecentOrders();
    loadTopProducts();
    loadSalesChart();
    loadCategoryChart();
}

function loadRecentOrders() {
    const tbody = document.getElementById('ordersTableBody');
    tbody.innerHTML = mockData.orders.slice(0, 5).map(order => `
        <tr>
            <td>${order.id}</td>
            <td>${order.customer}</td>
            <td><span class="user-id" onclick="viewUserDetails('${order.userId}')">${order.userId}</span></td>
            <td>$${order.amount.toFixed(2)}</td>
            <td><span class="status-badge status-${order.status}">${order.status}</span></td>
            <td>${order.date}</td>
        </tr>
    `).join('');
}

function loadTopProducts() {
    const container = document.getElementById('topProductsList');
    container.innerHTML = mockData.products
        .sort((a, b) => b.sales - a.sales)
        .slice(0, 5)
        .map((product, index) => `
            <div class="product-item">
                <div class="product-rank">
                    <span class="rank-number">${index + 1}</span>
                    <div class="product-info">
                        <h4>${product.name}</h4>
                        <p>${product.category}</p>
                    </div>
                </div>
                <div class="product-sales">
                    $${product.sales.toLocaleString()}
                </div>
            </div>
        `).join('');
}

function loadSalesChart() {
    const ctx = document.getElementById('salesChart');
    if (!ctx) return;
    
    new Chart(ctx, {
        type: 'line',
        data: {
            labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul'],
            datasets: [{
                label: 'Sales ($)',
                data: [12000, 19000, 15000, 25000, 22000, 30000, 28000],
                borderColor: '#4CAF50',
                backgroundColor: 'rgba(76, 175, 80, 0.1)',
                tension: 0.4,
                fill: true
            }]
        },
        options: {
            responsive: true,
            plugins: {
                legend: {
                    position: 'top',
                }
            }
        }
    });
}

function loadCategoryChart() {
    const ctx = document.getElementById('categoryChart');
    if (!ctx) return;
    
    new Chart(ctx, {
        type: 'doughnut',
        data: {
            labels: ['Electronics', 'Fashion', 'Sports', 'Men Clothes', 'Women Clothes'],
            datasets: [{
                data: [35, 25, 15, 12, 13],
                backgroundColor: [
                    '#FF6384',
                    '#36A2EB',
                    '#FFCE56',
                    '#4CAF50',
                    '#9966FF'
                ]
            }]
        },
        options: {
            responsive: true,
            plugins: {
                legend: {
                    position: 'right',
                }
            }
        }
    });
}

// =========================
// ORDERS MANAGEMENT
// =========================
function loadOrders() {
    mainContainer.innerHTML = `
        <div class="orders-view">
            <div class="view-header">
                <h1><i class="fa-solid fa-address-book"></i> Orders Management</h1>
                <div class="header-actions">
                    <div class="search-box">
                        <i class="fa-solid fa-search"></i>
                        <input type="text" id="orderSearch" placeholder="Search orders...">
                    </div>
                    <div class="filter-actions">
                        <select id="statusFilter">
                            <option value="">All Status</option>
                            <option value="pending">Pending</option>
                            <option value="processing">Processing</option>
                            <option value="shipped">Shipped</option>
                            <option value="delivered">Delivered</option>
                            <option value="cancelled">Cancelled</option>
                        </select>
                        <button class="btn btn-secondary" id="refreshOrders">
                            <i class="fa-solid fa-rotate"></i> Refresh
                        </button>
                    </div>
                </div>
            </div>

            <div class="view-content" id="ordersContent">
                <!-- Orders will be loaded here -->
            </div>
        </div>
    `;

    displayOrders();
    setupOrdersFilters();
}

function displayOrders(orders = mockData.orders) {
    const container = document.getElementById('ordersContent');
    if (!container) return;
    
    container.innerHTML = orders.map(order => `
        <div class="order-card">
            <div class="order-header">
                <div class="order-info">
                    <h3>${order.id} - ${order.customer}</h3>
                    <p>${order.email} | User ID: <span class="user-id" onclick="viewUserDetails('${order.userId}')">${order.userId}</span></p>
                </div>
                <div class="order-actions">
                    <span class="status-badge status-${order.status}">${order.status}</span>
                    <button class="btn-icon" onclick="viewOrder('${order.id}')" title="View Details">
                        <i class="fa-solid fa-eye"></i>
                    </button>
                    <button class="btn-icon" onclick="updateOrderStatus('${order.id}')" title="Update Status">
                        <i class="fa-solid fa-pen-to-square"></i>
                    </button>
                </div>
            </div>
            <div class="order-details">
                <div class="detail-item">
                    <h4>Amount</h4>
                    <p>$${order.amount.toFixed(2)}</p>
                </div>
                <div class="detail-item">
                    <h4>Date</h4>
                    <p>${order.date}</p>
                </div>
                <div class="detail-item">
                    <h4>User ID</h4>
                    <p><span class="user-id" onclick="viewUserDetails('${order.userId}')">${order.userId}</span></p>
                </div>
                <div class="detail-item">
                    <h4>Items</h4>
                    <p>${order.items.map(item => `${item.name} (x${item.quantity})`).join(', ')}</p>
                </div>
                <div class="detail-item">
                    <h4>Actions</h4>
                    <div style="display: flex; gap: 10px;">
                        <button class="btn-icon" onclick="printInvoice('${order.id}')" title="Print Invoice">
                            <i class="fa-solid fa-print"></i>
                        </button>
                        <button class="btn-icon" onclick="deleteOrder('${order.id}')" title="Delete Order">
                            <i class="fa-solid fa-trash"></i>
                        </button>
                    </div>
                </div>
            </div>
        </div>
    `).join('');
}

function setupOrdersFilters() {
    const searchInput = document.getElementById('orderSearch');
    const statusFilter = document.getElementById('statusFilter');
    
    if (searchInput) {
        searchInput.addEventListener('input', (e) => {
            const searchTerm = e.target.value.toLowerCase();
            const filteredOrders = mockData.orders.filter(order => 
                order.customer.toLowerCase().includes(searchTerm) ||
                order.id.toLowerCase().includes(searchTerm) ||
                order.email.toLowerCase().includes(searchTerm) ||
                order.userId.toLowerCase().includes(searchTerm)
            );
            displayOrders(filteredOrders);
        });
    }
    
    if (statusFilter) {
        statusFilter.addEventListener('change', (e) => {
            const status = e.target.value;
            const filteredOrders = status ? 
                mockData.orders.filter(order => order.status === status) : 
                mockData.orders;
            displayOrders(filteredOrders);
        });
    }
}

// =========================
// ADD PRODUCT
// =========================
function loadAddProduct() {
    mainContainer.innerHTML = `
        <div class="add-product">
            <div class="form-header">
                <h1><i class="fa-solid fa-square-plus"></i> Add New Product</h1>
                <p>Fill in the details to add a new product to your store</p>
            </div>

            <form class="product-form" id="productForm">
                <div class="form-grid">
                    <div class="form-group">
                        <label for="productName" class="form-label">
                            <i class="fa-solid fa-tag"></i> Product Name *
                        </label>
                        <input type="text" id="productName" name="name" required 
                               placeholder="Enter product name">
                    </div>

                    <div class="form-group">
                        <label for="productPrice" class="form-label">
                            <i class="fa-solid fa-dollar-sign"></i> Price *
                        </label>
                        <input type="number" id="productPrice" name="price" 
                               step="0.01" min="0" required placeholder="0.00">
                    </div>

                    <div class="form-group">
                        <label for="productCategory" class="form-label">
                            <i class="fa-solid fa-layer-group"></i> Category *
                        </label>
                        <select id="productCategory" name="category" required>
                            <option value="">Select Category</option>
                            <option value="electronics">Electronics</option>
                            <option value="fashion">Fashion</option>
                            <option value="sports">Sports</option>
                            <option value="men-clothes">Men Clothes</option>
                            <option value="women-clothes">Women Clothes</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="productStock" class="form-label">
                            <i class="fa-solid fa-boxes-stacked"></i> Stock Quantity *
                        </label>
                        <input type="number" id="productStock" name="stock" 
                               min="0" required placeholder="0">
                    </div>

                    <div class="form-group full-width">
                        <label for="productImage" class="form-label">
                            <i class="fa-solid fa-image"></i> Product Image URL
                        </label>
                        <input type="url" id="productImage" name="imageUrl" 
                               placeholder="https://example.com/image.jpg">
                    </div>

                    <div class="form-group full-width">
                        <label for="productDescription" class="form-label">
                            <i class="fa-solid fa-align-left"></i> Description
                        </label>
                        <textarea id="productDescription" name="description" 
                                  rows="4" placeholder="Enter product description..."></textarea>
                    </div>
                </div>

                <div class="form-actions">
                    <button type="button" class="btn btn-secondary" id="cancelBtn">
                        <i class="fa-solid fa-times"></i> Cancel
                    </button>
                    <button type="reset" class="btn btn-secondary">
                        <i class="fa-solid fa-eraser"></i> Clear Form
                    </button>
                    <button type="submit" class="btn btn-primary">
                        <i class="fa-solid fa-plus"></i> Add Product
                    </button>
                </div>
            </form>

            <div class="form-notification" id="formNotification"></div>
        </div>
    `;

    setupAddProductForm();
}

function setupAddProductForm() {
    const form = document.getElementById('productForm');
    const notification = document.getElementById('formNotification');
    const cancelBtn = document.getElementById('cancelBtn');
    
    if (form) {
        form.addEventListener('submit', (e) => {
            e.preventDefault();
            
            const formData = new FormData(form);
            const product = Object.fromEntries(formData.entries());
            
            // Generate ID and add to mock data
            product.id = mockData.products.length + 1;
            product.sales = 0;
            product.price = parseFloat(product.price);
            product.stock = parseInt(product.stock);
            mockData.products.push(product);
            
            // Show success message
            notification.innerHTML = `
                <div class="success-message">
                    <i class="fa-solid fa-check-circle"></i>
                    Product "${product.name}" added successfully!
                </div>
            `;
            
            // Reset form
            form.reset();
            
            // Auto-hide notification
            setTimeout(() => {
                notification.innerHTML = '';
            }, 3000);
        });
    }
    
    if (cancelBtn) {
        cancelBtn.addEventListener('click', () => {
            loadDashboard();
            setActiveButton('dashboard');
        });
    }
}

// =========================
// CUSTOMERS MANAGEMENT
// =========================
function loadCustomers() {
    mainContainer.innerHTML = `
        <div class="customers-view">
            <div class="view-header">
                <h1><i class="fa-solid fa-users"></i> Customers Management</h1>
                <div class="header-actions">
                    <div class="search-box">
                        <i class="fa-solid fa-search"></i>
                        <input type="text" id="customerSearch" placeholder="Search customers...">
                    </div>
                </div>
            </div>

            <div class="view-content" id="customersContent">
                <!-- Customers will be loaded here -->
            </div>
        </div>
    `;

    displayCustomers();
    setupCustomerSearch();
}

function displayCustomers(customers = mockData.customers) {
    const container = document.getElementById('customersContent');
    if (!container) return;
    
    container.innerHTML = customers.map(customer => `
        <div class="customer-card">
            <div class="customer-avatar">
                ${customer.name.split(' ').map(n => n[0]).join('')}
            </div>
            <div class="customer-info">
                <h3>${customer.name}</h3>
                <p>${customer.email}</p>
                <p>User ID: <span class="user-id">${customer.id}</span></p>
                <div class="customer-stats">
                    <div class="stat-item">
                        <i class="fa-solid fa-shopping-cart"></i>
                        <span>${customer.orders} orders</span>
                    </div>
                    <div class="stat-item">
                        <i class="fa-solid fa-dollar-sign"></i>
                        <span>$${customer.totalSpent}</span>
                    </div>
                    <div class="stat-item">
                        <i class="fa-solid fa-calendar"></i>
                        <span>Joined ${customer.joinDate}</span>
                    </div>
                </div>
            </div>
            <div class="customer-actions">
                <button class="btn-icon" onclick="viewCustomerDetails('${customer.id}')" title="View Details">
                    <i class="fa-solid fa-eye"></i>
                </button>
                <button class="btn-icon" onclick="sendEmail('${customer.email}')" title="Send Email">
                    <i class="fa-solid fa-envelope"></i>
                </button>
            </div>
        </div>
    `).join('');
}

function setupCustomerSearch() {
    const searchInput = document.getElementById('customerSearch');
    
    if (searchInput) {
        searchInput.addEventListener('input', (e) => {
            const searchTerm = e.target.value.toLowerCase();
            const filteredCustomers = mockData.customers.filter(customer => 
                customer.name.toLowerCase().includes(searchTerm) ||
                customer.email.toLowerCase().includes(searchTerm) ||
                customer.id.toLowerCase().includes(searchTerm)
            );
            displayCustomers(filteredCustomers);
        });
    }
}

// =========================
// REMOVE PRODUCTS
// =========================
function loadRemoveProduct() {
    mainContainer.innerHTML = `
        <div class="products-view">
            <div class="view-header">
                <h1><i class="fa-solid fa-trash-can"></i> Remove Products</h1>
                <div class="header-actions">
                    <div class="search-box">
                        <i class="fa-solid fa-search"></i>
                        <input type="text" id="productSearch" placeholder="Search products...">
                    </div>
                </div>
            </div>

            <div class="view-content">
                <div class="products-table">
                    <table>
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Name</th>
                                <th>Category</th>
                                <th>Price</th>
                                <th>Stock</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody id="productsTableBody">
                            <!-- Products will be loaded here -->
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    `;

    displayProductsForRemoval();
    setupProductSearch();
}

function displayProductsForRemoval() {
    const tbody = document.getElementById('productsTableBody');
    if (!tbody) return;
    
    tbody.innerHTML = mockData.products.map(product => `
        <tr>
            <td>${product.id}</td>
            <td>${product.name}</td>
            <td>${product.category}</td>
            <td>$${product.price.toFixed(2)}</td>
            <td>
                <span class="stock-badge ${product.stock > 10 ? 'in-stock' : product.stock > 0 ? 'low-stock' : 'out-of-stock'}">
                    ${product.stock}
                </span>
            </td>
            <td>
                <button class="btn btn-secondary" onclick="removeProduct(${product.id})">
                    <i class="fa-solid fa-trash"></i> Remove
                </button>
            </td>
        </tr>
    `).join('');
}

function setupProductSearch() {
    const searchInput = document.getElementById('productSearch');
    
    if (searchInput) {
        searchInput.addEventListener('input', (e) => {
            const searchTerm = e.target.value.toLowerCase();
            const rows = document.querySelectorAll('#productsTableBody tr');
            
            rows.forEach(row => {
                const text = row.textContent.toLowerCase();
                row.style.display = text.includes(searchTerm) ? '' : 'none';
            });
        });
    }
}

// =========================
// TRANSACTION LOG
// =========================
function loadTransactionLog() {
    mainContainer.innerHTML = `
        <div class="transactions-view">
            <div class="view-header">
                <h1><i class="fa-solid fa-tent-arrow-left-right"></i> Transaction Log</h1>
                <div class="header-actions">
                    <div class="search-box">
                        <i class="fa-solid fa-search"></i>
                        <input type="text" id="transactionSearch" placeholder="Search transactions...">
                    </div>
                    <div class="filter-actions">
                        <select id="typeFilter">
                            <option value="">All Types</option>
                            <option value="sale">Sales</option>
                            <option value="refund">Refunds</option>
                            <option value="fee">Fees</option>
                        </select>
                        <select id="userFilter">
                            <option value="">All Users</option>
                            ${mockData.customers.map(customer => 
                                `<option value="${customer.id}">${customer.name} (${customer.id})</option>`
                            ).join('')}
                            <option value="system">System</option>
                        </select>
                    </div>
                </div>
            </div>

            <div class="view-content">
                <div class="transaction-table">
                    <div class="transaction-row header">
                        <div>Transaction ID</div>
                        <div>Type</div>
                        <div>Description</div>
                        <div>User ID</div>
                        <div>Amount</div>
                        <div>Date</div>
                        <div>Status</div>
                    </div>
                    <div id="transactionsList">
                        <!-- Transactions will be loaded here -->
                    </div>
                </div>
                <div class="transaction-stats">
                    <h3><i class="fa-solid fa-chart-bar"></i> Transaction Statistics</h3>
                    <div id="transactionStats" style="display: grid; grid-template-columns: repeat(auto-fit, minmax(200px, 1fr)); gap: 20px; margin-top: 15px;">
                        <!-- Stats will be loaded here -->
                    </div>
                </div>
            </div>
        </div>
    `;

    displayTransactions();
    displayTransactionStats();
    setupTransactionFilters();
}

function displayTransactions(transactions = mockData.transactions) {
    const container = document.getElementById('transactionsList');
    if (!container) return;
    
    container.innerHTML = transactions.map(txn => `
        <div class="transaction-row">
            <div><strong>${txn.id}</strong></div>
            <div><span class="status-badge status-${txn.type}">${txn.type}</span></div>
            <div>${txn.description}</div>
            <div>
                <span class="user-id" onclick="viewUserDetails('${txn.userId}')" title="Click to view user details">
                    ${txn.userId}
                </span>
            </div>
            <div class="amount ${txn.amount > 0 ? 'credit' : 'debit'}">
                ${txn.amount > 0 ? '+' : ''}$${Math.abs(txn.amount).toFixed(2)}
            </div>
            <div>${txn.date}</div>
            <div><span class="status-badge status-${txn.status}">${txn.status}</span></div>
        </div>
    `).join('');
}

function displayTransactionStats() {
    const statsContainer = document.getElementById('transactionStats');
    if (!statsContainer) return;
    
    const totalTransactions = mockData.transactions.length;
    const totalSales = mockData.transactions
        .filter(t => t.type === 'sale')
        .reduce((sum, t) => sum + t.amount, 0);
    const totalRefunds = Math.abs(mockData.transactions
        .filter(t => t.type === 'refund')
        .reduce((sum, t) => sum + t.amount, 0));
    const uniqueUsers = new Set(mockData.transactions.map(t => t.userId)).size;
    
    statsContainer.innerHTML = `
        <div class="stat-item" style="text-align: center; padding: 15px; background: var(--light-bg); border-radius: var(--border-radius);">
            <h4 style="color: var(--text-light); margin-bottom: 5px;">Total Transactions</h4>
            <p style="font-size: 1.5rem; font-weight: bold; color: var(--primary-color);">${totalTransactions}</p>
        </div>
        <div class="stat-item" style="text-align: center; padding: 15px; background: var(--light-bg); border-radius: var(--border-radius);">
            <h4 style="color: var(--text-light); margin-bottom: 5px;">Total Sales</h4>
            <p style="font-size: 1.5rem; font-weight: bold; color: var(--success-color);">$${totalSales.toFixed(2)}</p>
        </div>
        <div class="stat-item" style="text-align: center; padding: 15px; background: var(--light-bg); border-radius: var(--border-radius);">
            <h4 style="color: var(--text-light); margin-bottom: 5px;">Total Refunds</h4>
            <p style="font-size: 1.5rem; font-weight: bold; color: var(--danger-color);">$${totalRefunds.toFixed(2)}</p>
        </div>
        <div class="stat-item" style="text-align: center; padding: 15px; background: var(--light-bg); border-radius: var(--border-radius);">
            <h4 style="color: var(--text-light); margin-bottom: 5px;">Unique Users</h4>
            <p style="font-size: 1.5rem; font-weight: bold; color: var(--accent-color);">${uniqueUsers}</p>
        </div>
    `;
}

function setupTransactionFilters() {
    const searchInput = document.getElementById('transactionSearch');
    const typeFilter = document.getElementById('typeFilter');
    const userFilter = document.getElementById('userFilter');
    
    if (searchInput) {
        searchInput.addEventListener('input', (e) => {
            const searchTerm = e.target.value.toLowerCase();
            const filteredTransactions = mockData.transactions.filter(txn => 
                txn.id.toLowerCase().includes(searchTerm) ||
                txn.description.toLowerCase().includes(searchTerm) ||
                txn.userId.toLowerCase().includes(searchTerm)
            );
            displayTransactions(filteredTransactions);
        });
    }
    
    if (typeFilter) {
        typeFilter.addEventListener('change', (e) => {
            applyTransactionFilters();
        });
    }
    
    if (userFilter) {
        userFilter.addEventListener('change', (e) => {
            applyTransactionFilters();
        });
    }
}

function applyTransactionFilters() {
    const type = document.getElementById('typeFilter')?.value || '';
    const userId = document.getElementById('userFilter')?.value || '';
    
    let filteredTransactions = mockData.transactions;
    
    if (type) {
        filteredTransactions = filteredTransactions.filter(txn => txn.type === type);
    }
    
    if (userId) {
        filteredTransactions = filteredTransactions.filter(txn => txn.userId === userId);
    }
    
    displayTransactions(filteredTransactions);
}

// =========================
// VIEW USER DETAILS FROM TRANSACTION
// =========================
function viewUserDetails(userId) {
    if (userId === 'system') {
        alert('System Transaction\nThis is an automated system transaction.');
        return;
    }
    
    const customer = mockData.customers.find(c => c.id === userId);
    if (customer) {
        const userTransactions = mockData.transactions.filter(t => t.userId === userId);
        const totalSpent = userTransactions
            .filter(t => t.type === 'sale')
            .reduce((sum, t) => sum + t.amount, 0);
        const totalRefunds = Math.abs(userTransactions
            .filter(t => t.type === 'refund')
            .reduce((sum, t) => sum + t.amount, 0));
        
        const userOrders = mockData.orders.filter(o => o.userId === userId);
        
        alert(`User Details:\n
👤 Name: ${customer.name}
📧 Email: ${customer.email}
🆔 User ID: ${customer.id}
📅 Joined: ${customer.joinDate}
📊 Orders: ${userOrders.length}
💰 Total Spent: $${totalSpent.toFixed(2)}
🔄 Total Refunds: $${totalRefunds.toFixed(2)}
📈 Net Spending: $${(totalSpent - totalRefunds).toFixed(2)}
🎯 Transaction Count: ${userTransactions.length}`);
    } else {
        alert(`User ID: ${userId}\nNo detailed information available for this user.`);
    }
}

// =========================
// CART MANAGEMENT
// =========================
function loadCarts() {
    mainContainer.innerHTML = `
        <div class="carts-view">
            <h1><i class="fa-solid fa-cart-plus"></i> Shopping Carts</h1>
            <p style="text-align: center; color: var(--text-light); margin-top: 50px;">
                <i class="fa-solid fa-tools" style="font-size: 3rem; margin-bottom: 20px; display: block;"></i>
                Cart management feature is coming soon!
            </p>
        </div>
    `;
}

// =========================
// SETTINGS
// =========================
function loadSettings() {
    mainContainer.innerHTML = `
        <div class="settings-view">
            <div class="settings-section">
                <h2><i class="fa-solid fa-store"></i> Store Settings</h2>
                <div class="setting-item">
                    <div class="setting-info">
                        <h3>Store Name</h3>
                        <p>Display name of your store</p>
                    </div>
                    <input type="text" class="setting-input" value="Gihon e-com" placeholder="Enter store name">
                </div>
                <div class="setting-item">
                    <div class="setting-info">
                        <h3>Store Email</h3>
                        <p>Contact email for your store</p>
                    </div>
                    <input type="email" class="setting-input" value="admin@gihon.com" placeholder="Enter store email">
                </div>
                <div class="setting-item">
                    <div class="setting-info">
                        <h3>Store Currency</h3>
                        <p>Default currency for transactions</p>
                    </div>
                    <select class="setting-input">
                        <option value="USD" selected>US Dollar ($)</option>
                        <option value="EUR">Euro (€)</option>
                        <option value="GBP">British Pound (£)</option>
                        <option value="INR">Indian Rupee (₹)</option>
                    </select>
                </div>
            </div>

            <div class="settings-section">
                <h2><i class="fa-solid fa-bell"></i> Notification Settings</h2>
                <div class="setting-item">
                    <div class="setting-info">
                        <h3>Email Notifications</h3>
                        <p>Receive email notifications for new orders</p>
                    </div>
                    <label class="switch">
                        <input type="checkbox" checked>
                        <span class="slider"></span>
                    </label>
                </div>
                <div class="setting-item">
                    <div class="setting-info">
                        <h3>SMS Notifications</h3>
                        <p>Receive SMS notifications for important updates</p>
                    </div>
                    <label class="switch">
                        <input type="checkbox">
                        <span class="slider"></span>
                    </label>
                </div>
            </div>

            <div class="settings-section">
                <h2><i class="fa-solid fa-shield-halved"></i> Security</h2>
                <div class="setting-item">
                    <div class="setting-info">
                        <h3>Two-Factor Authentication</h3>
                        <p>Add an extra layer of security to your account</p>
                    </div>
                    <label class="switch">
                        <input type="checkbox">
                        <span class="slider"></span>
                    </label>
                </div>
                <div class="setting-item">
                    <div class="setting-info">
                        <h3>Session Timeout</h3>
                        <p>Automatically logout after inactivity</p>
                    </div>
                    <select class="setting-input">
                        <option value="15">15 minutes</option>
                        <option value="30" selected>30 minutes</option>
                        <option value="60">1 hour</option>
                        <option value="120">2 hours</option>
                    </select>
                </div>
            </div>

            <div class="form-actions">
                <button class="btn btn-secondary" onclick="loadDashboard()">
                    <i class="fa-solid fa-times"></i> Cancel
                </button>
                <button class="btn btn-primary" onclick="saveSettings()">
                    <i class="fa-solid fa-save"></i> Save Settings
                </button>
            </div>
        </div>
    `;
}

// =========================
// ACTION FUNCTIONS
// =========================
function viewOrder(orderId) {
    alert(`View order details for ${orderId}`);
}

function updateOrderStatus(orderId) {
    const newStatus = prompt('Enter new status (pending, processing, shipped, delivered, cancelled):');
    if (newStatus) {
        const order = mockData.orders.find(o => o.id === orderId);
        if (order) {
            order.status = newStatus;
            alert(`Order ${orderId} status updated to ${newStatus}`);
            if (currentView === 'orders') {
                loadOrders();
            } else if (currentView === 'dashboard') {
                loadDashboard();
            }
        }
    }
}

function printInvoice(orderId) {
    alert(`Printing invoice for order ${orderId}`);
}

function deleteOrder(orderId) {
    if (confirm(`Are you sure you want to delete order ${orderId}?`)) {
        mockData.orders = mockData.orders.filter(o => o.id !== orderId);
        alert(`Order ${orderId} deleted successfully`);
        if (currentView === 'orders') {
            loadOrders();
        } else if (currentView === 'dashboard') {
            loadDashboard();
        }
    }
}

function removeProduct(productId) {
    if (confirm('Are you sure you want to remove this product?')) {
        mockData.products = mockData.products.filter(p => p.id !== productId);
        displayProductsForRemoval();
        alert('Product removed successfully');
    }
}

function viewCustomerDetails(customerId) {
    const customer = mockData.customers.find(c => c.id === customerId);
    if (customer) {
        alert(`Customer Details:\n\nName: ${customer.name}\nEmail: ${customer.email}\nUser ID: ${customer.id}\nOrders: ${customer.orders}\nTotal Spent: $${customer.totalSpent}\nJoined: ${customer.joinDate}`);
    }
}

function sendEmail(email) {
    const subject = prompt('Enter email subject:');
    if (subject) {
        const body = prompt('Enter email message:');
        if (body) {
            alert(`Email sent to ${email}\nSubject: ${subject}\nMessage: ${body}`);
        }
    }
}

function saveSettings() {
    alert('Settings saved successfully!');
    loadDashboard();
    setActiveButton('dashboard');
}