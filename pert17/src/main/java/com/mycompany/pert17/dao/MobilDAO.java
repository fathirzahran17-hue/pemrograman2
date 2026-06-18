package com.mycompany.pert17.dao;

import com.mycompany.pert17.model.Mobil;
import com.mycompany.pert17.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MobilDAO {

    public List<Mobil> getAllMobil() {
        List<Mobil> list = new ArrayList<>();
        String query = "SELECT * FROM mobil";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Mobil(rs.getString("nopol"), rs.getString("merk"), rs.getString("tipe"),
                        rs.getInt("tahun"), rs.getDouble("harga"), rs.getString("status")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Mobil getMobil(String nopol) {
        Mobil mobil = null;
        String query = "SELECT * FROM mobil WHERE nopol=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, nopol);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    mobil = new Mobil(rs.getString("nopol"), rs.getString("merk"), rs.getString("tipe"),
                            rs.getInt("tahun"), rs.getDouble("harga"), rs.getString("status"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mobil;
    }

    public boolean addMobil(Mobil mobil) {
        String query = "INSERT INTO mobil (nopol, merk, tipe, tahun, harga, status) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, mobil.getNopol());
            ps.setString(2, mobil.getMerk());
            ps.setString(3, mobil.getTipe());
            ps.setInt(4, mobil.getTahun());
            ps.setDouble(5, mobil.getHarga());
            ps.setString(6, mobil.getStatus());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateMobil(Mobil mobil) {
        String query = "UPDATE mobil SET merk=?, tipe=?, tahun=?, harga=?, status=? WHERE nopol=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, mobil.getMerk());
            ps.setString(2, mobil.getTipe());
            ps.setInt(3, mobil.getTahun());
            ps.setDouble(4, mobil.getHarga());
            ps.setString(5, mobil.getStatus());
            ps.setString(6, mobil.getNopol());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteMobil(String nopol) {
        String query = "DELETE FROM mobil WHERE nopol=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, nopol);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
