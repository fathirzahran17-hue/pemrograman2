<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>RentCar - Master Mobil</title>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <nav>
        <a href="index.jsp" class="brand">RentCar</a>
        <div class="links">
            <a href="index.jsp">Dashboard</a>
            <a href="mobil" style="color: var(--primary);">Data Mobil</a>
            <a href="customer">Data Customer</a>
            <a href="transaksi">Transaksi</a>
            <a href="transaksi?action=laporan">Laporan</a>
        </div>
    </nav>

    <div class="container">
        <div class="card">
            <div class="page-header">
                <h2>Master Data Mobil</h2>
            </div>
            
            <form action="mobil" method="post" style="display: grid; grid-template-columns: 1fr 1fr; gap: 1rem;">
                <input type="hidden" name="action" value="add">
                <div class="form-group">
                    <label>No Polisi</label>
                    <input type="text" name="nopol" class="form-control" required placeholder="B 1234 ABC">
                </div>
                <div class="form-group">
                    <label>Merk</label>
                    <input type="text" name="merk" class="form-control" required placeholder="Toyota">
                </div>
                <div class="form-group">
                    <label>Tipe</label>
                    <input type="text" name="tipe" class="form-control" required placeholder="Avanza">
                </div>
                <div class="form-group">
                    <label>Tahun</label>
                    <input type="number" name="tahun" class="form-control" required placeholder="2022">
                </div>
                <div class="form-group">
                    <label>Harga Sewa / Hari</label>
                    <input type="number" name="harga" class="form-control" required placeholder="300000">
                </div>
                <div class="form-group">
                    <label>Status</label>
                    <select name="status" class="form-control">
                        <option value="Tersedia">Tersedia</option>
                        <option value="Disewa">Disewa</option>
                    </select>
                </div>
                <div class="form-group" style="grid-column: span 2;">
                    <button type="submit" class="btn btn-primary">Simpan Data Mobil</button>
                </div>
            </form>
        </div>

        <div class="card" style="margin-top: 2rem;">
            <h3>Daftar Mobil</h3>
            <div class="table-container">
                <table>
                    <thead>
                        <tr>
                            <th>No Polisi</th>
                            <th>Merk</th>
                            <th>Tipe</th>
                            <th>Tahun</th>
                            <th>Harga Sewa</th>
                            <th>Status</th>
                            <th>Aksi</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="m" items="${listMobil}">
                            <tr>
                                <td>${m.nopol}</td>
                                <td>${m.merk}</td>
                                <td>${m.tipe}</td>
                                <td>${m.tahun}</td>
                                <td>Rp ${m.harga}</td>
                                <td>
                                    <span class="badge ${m.status == 'Tersedia' ? 'badge-tersedia' : 'badge-disewa'}">
                                        ${m.status}
                                    </span>
                                </td>
                                <td>
                                    <a href="mobil?action=delete&nopol=${m.nopol}" class="btn btn-danger" style="padding: 0.4rem 0.8rem; font-size: 0.8rem;" onclick="return confirm('Hapus mobil ini?')">Hapus</a>
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
