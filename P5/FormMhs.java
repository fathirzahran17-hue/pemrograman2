package pert5;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class FormMhs extends JFrame {

    JTextField txtNim, txtNama, txtSemester, txtKelas;
    JButton btnSimpan;
    JTable table;
    DefaultTableModel model;

    public FormMhs() {
        setTitle("Data Mahasiswa");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // PANEL INPUT
        JPanel panelInput = new JPanel(new GridLayout(5, 2));

        panelInput.add(new JLabel("NIM"));
        txtNim = new JTextField();
        panelInput.add(txtNim);

        panelInput.add(new JLabel("Nama"));
        txtNama = new JTextField();
        panelInput.add(txtNama);

        panelInput.add(new JLabel("Semester"));
        txtSemester = new JTextField();
        panelInput.add(txtSemester);

        panelInput.add(new JLabel("Kelas"));
        txtKelas = new JTextField();
        panelInput.add(txtKelas);

        btnSimpan = new JButton("Simpan");
        panelInput.add(btnSimpan);

        add(panelInput, BorderLayout.NORTH);

        // TABLE
        model = new DefaultTableModel();
        model.addColumn("NIM");
        model.addColumn("Nama");
        model.addColumn("Semester");
        model.addColumn("Kelas");

        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // EVENT BUTTON
        btnSimpan.addActionListener(e -> simpanData());

        tampilData();
    }

    private void simpanData() {
        try {
            Connection conn = Koneksi.getKoneksi();

            PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO mahasiswa VALUES (?, ?, ?, ?)"
            );

            ps.setString(1, txtNim.getText());
            ps.setString(2, txtNama.getText());
            ps.setString(3, txtSemester.getText());
            ps.setString(4, txtKelas.getText());

            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Data berhasil disimpan");
            tampilData();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void tampilData() {
        model.setRowCount(0);
        try {
            Connection conn = Koneksi.getKoneksi();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM mahasiswa");

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("nim"),
                    rs.getString("nama"),
                    rs.getString("semester"),
                    rs.getString("kelas")
                });
            }

        } catch (Exception e) {
            System.out.println("Error tampil: " + e.getMessage());
        }
    }
}