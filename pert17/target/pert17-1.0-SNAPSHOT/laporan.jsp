<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>RentCar - Laporan Transaksi</title>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/style.css">
    <style>
        @media print {
            nav, .btn, .page-header { display: none; }
            body { background: white; color: black; }
            .card { border: none; box-shadow: none; background: transparent; padding: 0;}
            table th { background: #eee; color: black; }
            table th, table td { border: 1px solid #ccc; color: black; }
            .badge { border: 1px solid #666; background: transparent; color: black !important; }
        }
    </style>
</head>
<body>
    <nav>
        <a href="index.jsp" class="brand">RentCar</a>
        <div class="links">
            <a href="index.jsp">Dashboard</a>
            <a href="mobil">Data Mobil</a>
            <a href="customer">Data Customer</a>
            <a href="transaksi">Transaksi</a>
            <a href="transaksi?action=laporan" style="color: var(--primary);">Laporan</a>
        </div>
    </nav>

    <div class="container">
        <div class="card">
            <div class="page-header">
                <h2>Laporan Riwayat Transaksi</h2>
                <button class="btn btn-primary" onclick="window.print()">Cetak Laporan</button>
            </div>
            
            <div class="table-container">
                <table>
                    <thead>
                        <tr>
                            <th>ID Transaksi</th>
                            <th>NIK Customer</th>
                            <th>Nopol Mobil</th>
                            <th>Tgl Sewa</th>
                            <th>Tgl Kembali</th>
                            <th>Total Harga (Est/Real)</th>
                            <th>Status</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="t" items="${listTransaksi}">
                            <tr>
                                <td>TRX-${t.id}</td>
                                <td>${t.nikCustomer}</td>
                                <td>${t.nopolMobil}</td>
                                <td>${t.tglSewa}</td>
                                <td>${t.tglKembali == null ? '-' : t.tglKembali}</td>
                                <td>Rp ${t.totalHarga}</td>
                                <td>
                                    <span class="badge ${t.status == 'Selesai' ? 'badge-tersedia' : 'badge-disewa'}">
                                        ${t.status}
                                    </span>
                                </td>
                            </tr>
                        </c:forEach>
                        <c:if test="${empty listTransaksi}">
                            <tr>
                                <td colspan="7" style="text-align: center;">Tidak ada data transaksi</td>
                            </tr>
                        </c:if>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html>
