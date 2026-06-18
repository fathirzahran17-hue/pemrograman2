<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>RentCar - Dashboard</title>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <nav>
        <a href="index.jsp" class="brand">RentCar</a>
        <div class="links">
            <a href="index.jsp">Dashboard</a>
            <a href="mobil">Data Mobil</a>
            <a href="customer">Data Customer</a>
            <a href="transaksi">Transaksi</a>
            <a href="transaksi?action=laporan">Laporan</a>
        </div>
    </nav>

    <div class="container">
        <div class="card">
            <div class="page-header">
                <h2>Welcome to RentCar Admin System</h2>
            </div>
            <p style="color: var(--text-muted); line-height: 1.6;">
                Manage your car rental business efficiently. Navigate through the menu to manage cars, customers, and daily transactions.
            </p>

            <div class="dashboard-grid">
                <div class="stat-card">
                    <h3>Total Cars Managed</h3>
                    <p>Manage</p>
                    <br>
                    <a href="mobil" class="btn btn-primary" style="font-size: 0.9rem;">View Cars</a>
                </div>
                <div class="stat-card">
                    <h3>Registered Customers</h3>
                    <p>Track</p>
                    <br>
                    <a href="customer" class="btn btn-primary" style="font-size: 0.9rem;">View Customers</a>
                </div>
                <div class="stat-card">
                    <h3>Transactions</h3>
                    <p>Record</p>
                    <br>
                    <a href="transaksi" class="btn btn-primary" style="font-size: 0.9rem;">View Transactions</a>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
