<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>RentCar - Transaksi Penyewaan</title>
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
            <a href="transaksi" style="color: var(--primary);">Transaksi</a>
            <a href="transaksi?action=laporan">Laporan</a>
        </div>
    </nav>

    <div class="container">
        <div class="card">
            <div class="page-header">
                <h2>Proses Penyewaan</h2>
            </div>
            
            <form action="transaksi" method="post" style="display: grid; grid-template-columns: 1fr 1fr; gap: 1rem;">
                <div class="form-group">
                    <label>Pilih Customer</label>
                    <select name="nikCustomer" class="form-control" required>
                        <option value="">-- Pilih Customer --</option>
                        <c:forEach var="c" items="${listCustomer}">
                            <option value="${c.nik}">${c.nik} - ${c.nama}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label>Pilih Mobil (Yang Tersedia)</label>
                    <select name="nopolMobil" class="form-control" required>
                        <option value="">-- Pilih Mobil --</option>
                        <c:forEach var="m" items="${listMobil}">
                            <c:if test="${m.status == 'Tersedia'}">
                                <option value="${m.nopol}">${m.nopol} - ${m.merk} ${m.tipe} (Rp ${m.harga}/hari)</option>
                            </c:if>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label>Tanggal Sewa</label>
                    <input type="date" name="tglSewa" class="form-control" required>
                </div>
                <div class="form-group">
                    <label>Rencana Tanggal Kembali (Opsional)</label>
                    <input type="date" name="tglKembali" class="form-control">
                </div>
                <div class="form-group" style="grid-column: span 2;">
                    <button type="submit" class="btn btn-primary">Proses Sewa</button>
                </div>
            </form>
        </div>

        <div class="card" style="margin-top: 2rem;">
            <h3>Transaksi Aktif (Pengembalian)</h3>
            <div class="table-container">
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Customer NIK</th>
                            <th>Mobil Nopol</th>
                            <th>Tgl Sewa</th>
                            <th>Status</th>
                            <th>Aksi</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="t" items="${listTransaksi}">
                            <c:if test="${t.status == 'Aktif'}">
                                <tr>
                                    <td>${t.id}</td>
                                    <td>${t.nikCustomer}</td>
                                    <td>${t.nopolMobil}</td>
                                    <td>${t.tglSewa}</td>
                                    <td>
                                        <span class="badge badge-disewa">${t.status}</span>
                                    </td>
                                    <td>
                                        <a href="transaksi?action=kembali&id=${t.id}&nopol=${t.nopolMobil}" class="btn btn-success" style="padding: 0.4rem 0.8rem; font-size: 0.8rem;" onclick="return confirm('Proses pengembalian mobil ini?')">Kembalikan Mobil</a>
                                    </td>
                                </tr>
                            </c:if>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html>
