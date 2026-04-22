package P6;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MahasiswaDAO {

    public List<Mahasiswa> search(String keyword, String kategori) {

        List<Mahasiswa> list = new ArrayList<>();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = Koneksi.getConnection();

            String sql;

            // ================= SQL =================
            if (keyword == null || keyword.trim().isEmpty()) {

                sql = "SELECT * FROM mahasiswa";
                ps = conn.prepareStatement(sql);

            } else if (kategori.equals("Semua")) {

                sql = "SELECT * FROM mahasiswa WHERE " +
                      "nim LIKE ? OR nama LIKE ? OR semester LIKE ? OR kelas LIKE ?";
                ps = conn.prepareStatement(sql);

                String key = "%" + keyword + "%";
                ps.setString(1, key);
                ps.setString(2, key);
                ps.setString(3, key);
                ps.setString(4, key);

            } else {

                // Convert kategori (example: "Nama" → "nama")
                String kolom = kategori.toLowerCase();

                sql = "SELECT * FROM mahasiswa WHERE " + kolom + " LIKE ?";
                ps = conn.prepareStatement(sql);

                ps.setString(1, "%" + keyword + "%");
            }

            // ================= EXECUTE =================
            rs = ps.executeQuery();

            while (rs.next()) {
                Mahasiswa m = new Mahasiswa(
                        rs.getString("nim"),
                        rs.getString("nama"),
                        rs.getString("semester"),
                        rs.getString("kelas")
                );
                list.add(m);
            }

        } catch (Exception e) {
            System.out.println("Error DAO: " + e.getMessage());
        } finally {

            // ================= CLOSE RESOURCE =================
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }

        return list;
    }
}