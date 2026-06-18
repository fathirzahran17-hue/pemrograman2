<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>RentCar - Master Customer</title>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <nav>
        <a href="index.jsp" class="brand">RentCar</a>
        <div class="links">
            <a href="index.jsp">Dashboard</a>
            <a href="mobil">Data Mobil</a>
            <a href="customer" style="color: var(--primary);">Data Customer</a>
            <a href="transaksi">Transaksi</a>
            <a href="transaksi?action=laporan">Laporan</a>
        </div>
    </nav>

    <div class="container">
        <div class="card">
            <div class="page-header">
                <h2>Master Data Customer</h2>
            </div>
            
            <form action="customer" method="post" style="display: grid; grid-template-columns: 1fr 1fr; gap: 1rem;">
                <input type="hidden" name="action" value="add">
                <div class="form-group">
                    <label>NIK</label>
                    <input type="text" name="nik" class="form-control" required placeholder="16 digit NIK">
                </div>
                <div class="form-group">
                    <label>Nama Lengkap</label>
                    <input type="text" name="nama" class="form-control" required placeholder="Nama Sesuai KTP">
                </div>
                <div class="form-group" style="grid-column: span 2;">
                    <label>Alamat Lengkap</label>
                    <input type="text" name="alamat" class="form-control" required placeholder="Alamat Domisili">
                </div>
                <div class="form-group" style="grid-column: span 2;">
                    <label>No Telepon</label>
                    <input type="text" name="telp" class="form-control" required placeholder="08123456789">
                </div>
                <div class="form-group" style="grid-column: span 2;">
                    <button type="submit" class="btn btn-primary">Simpan Data Customer</button>
                </div>
            </form>
        </div>

        <div class="card" style="margin-top: 2rem;">
            <h3>Daftar Customer</h3>
            <div class="table-container">
                <table>
                    <thead>
                        <tr>
                            <th>NIK</th>
                            <th>Nama</th>
                            <th>Alamat</th>
                            <th>Telepon</th>
                            <th>Aksi</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="c" items="${listCustomer}">
                            <tr>
                                <td>${c.nik}</td>
                                <td>${c.nama}</td>
                                <td>${c.alamat}</td>
                                <td>${c.telp}</td>
                                <td>
                                    <a href="customer?action=delete&nik=${c.nik}" class="btn btn-danger" style="padding: 0.4rem 0.8rem; font-size: 0.8rem;" onclick="return confirm('Hapus customer ini?')">Hapus</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html>
