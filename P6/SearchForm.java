package P6;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class SearchForm extends JFrame {

    private JTextField txtSearch;
    private JButton btnSearch, btnReset;
    private JComboBox<String> cmbKategori;
    private JTable jTable1;
    private JLabel lblHasil;

    private DefaultTableModel model;
    private MahasiswaDAO dao = new MahasiswaDAO();

    public SearchForm() {
        initComponents();
        tampilData("", "Semua");
    }

    // ===================== TAMPIL DATA =====================
    private void tampilData(String keyword, String kategori) {
        model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);

        List<Mahasiswa> list = dao.search(keyword, kategori);

        for (Mahasiswa m : list) {
            model.addRow(new Object[]{
                m.getNim(),
                m.getNama(),
                m.getSemester(),
                m.getKelas()
            });
        }

        lblHasil.setText("Ditemukan: " + list.size() + " data");
    }

    // ===================== INIT UI =====================
    private void initComponents() {

        setTitle("Search Data Mahasiswa");
        setSize(650, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel jLabel1 = new JLabel("Search:");

        txtSearch = new JTextField();

        btnSearch = new JButton("Cari");
        btnReset  = new JButton("Reset");

        lblHasil = new JLabel("Ditemukan: 0 data");

        String[] kategori = {"Semua", "NIM", "Nama", "Semester", "Kelas"};
        cmbKategori = new JComboBox<>(kategori);

        jTable1 = new JTable();
        jTable1.setModel(new DefaultTableModel(
            new Object[][]{},
            new String[]{"NIM", "Nama", "Semester", "Kelas"}
        ));

        JScrollPane scrollPane = new JScrollPane(jTable1);

        // Layout
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        c.fill = GridBagConstraints.HORIZONTAL;

        // Row 0
        c.gridx = 0; c.gridy = 0; add(jLabel1, c);
        c.gridx = 1; add(cmbKategori, c);
        c.gridx = 2; c.weightx = 1.0; add(txtSearch, c);
        c.weightx = 0;
        c.gridx = 3; add(btnSearch, c);
        c.gridx = 4; add(btnReset, c);

        // Row 1
        c.gridx = 0; c.gridy = 1; c.gridwidth = 5;
        add(lblHasil, c);
        c.gridwidth = 1;

        // Row 2 (Table)
        c.gridx = 0; c.gridy = 2; c.gridwidth = 5;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0; c.weighty = 1.0;
        add(scrollPane, c);

        // ===================== ACTION =====================

        btnSearch.addActionListener(e -> cariData());

        btnReset.addActionListener(e -> {
            txtSearch.setText("");
            cmbKategori.setSelectedIndex(0);
            tampilData("", "Semua");
        });

        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent e) {
                cariData();
            }
        });
    }

    // ===================== CARI =====================
    private void cariData() {
        String keyword = txtSearch.getText().trim();
        String kategori = cmbKategori.getSelectedItem().toString();
        tampilData(keyword, kategori);
    }

    // ===================== MAIN =====================
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SearchForm().setVisible(true));
    }
}