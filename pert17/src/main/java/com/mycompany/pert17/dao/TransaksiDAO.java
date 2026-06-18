package com.mycompany.pert17.dao;

import com.mycompany.pert17.model.Transaksi;
import com.mycompany.pert17.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransaksiDAO {

    public List<Transaksi> getAllTransaksi() {
        List<Transaksi> list = new ArrayList<>();
        String query = "SELECT * FROM transaksi ORDER BY id DESC";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Transaksi(rs.getInt("id"), rs.getString("nik_customer"), rs.getString("nopol_mobil"),
                        rs.getDate("tgl_sewa"), rs.getDate("tgl_kembali"), rs.getDouble("total_harga"), rs.getString("status")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean addTransaksi(Transaksi trx) {
        String query = "INSERT INTO transaksi (nik_customer, nopol_mobil, tgl_sewa, tgl_kembali, total_harga, status) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, trx.getNikCustomer());
            ps.setString(2, trx.getNopolMobil());
            ps.setDate(3, trx.getTglSewa());
            ps.setDate(4, trx.getTglKembali());
            ps.setDouble(5, trx.getTotalHarga());
            ps.setString(6, trx.getStatus());
            
            int affected = ps.executeUpdate();
            if(affected > 0) {
                // Update status mobil menjadi Disewa
                String updateMobil = "UPDATE mobil SET status='Disewa' WHERE nopol=?";
                try(PreparedStatement ps2 = con.prepareStatement(updateMobil)) {
                    ps2.setString(1, trx.getNopolMobil());
                    ps2.executeUpdate();
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean returnMobil(int id, String nopol) {
        String query = "UPDATE transaksi SET status='Selesai', tgl_kembali=? WHERE id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setDate(1, new java.sql.Date(System.currentTimeMillis()));
            ps.setInt(2, id);
            int affected = ps.executeUpdate();
            if (affected > 0) {
                // Update status mobil menjadi Tersedia
                String updateMobil = "UPDATE mobil SET status='Tersedia' WHERE nopol=?";
                try(PreparedStatement ps2 = con.prepareStatement(updateMobil)) {
                    ps2.setString(1, nopol);
                    ps2.executeUpdate();
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
