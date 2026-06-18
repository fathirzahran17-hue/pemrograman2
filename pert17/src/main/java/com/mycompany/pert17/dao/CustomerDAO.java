package com.mycompany.pert17.dao;

import com.mycompany.pert17.model.Customer;
import com.mycompany.pert17.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    public List<Customer> getAllCustomer() {
        List<Customer> list = new ArrayList<>();
        String query = "SELECT * FROM customer";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Customer(rs.getString("nik"), rs.getString("nama"), rs.getString("alamat"), rs.getString("telp")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Customer getCustomer(String nik) {
        Customer customer = null;
        String query = "SELECT * FROM customer WHERE nik=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, nik);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    customer = new Customer(rs.getString("nik"), rs.getString("nama"), rs.getString("alamat"), rs.getString("telp"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    public boolean addCustomer(Customer customer) {
        String query = "INSERT INTO customer (nik, nama, alamat, telp) VALUES (?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, customer.getNik());
            ps.setString(2, customer.getNama());
            ps.setString(3, customer.getAlamat());
            ps.setString(4, customer.getTelp());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateCustomer(Customer customer) {
        String query = "UPDATE customer SET nama=?, alamat=?, telp=? WHERE nik=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, customer.getNama());
            ps.setString(2, customer.getAlamat());
            ps.setString(3, customer.getTelp());
            ps.setString(4, customer.getNik());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteCustomer(String nik) {
        String query = "DELETE FROM customer WHERE nik=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, nik);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
