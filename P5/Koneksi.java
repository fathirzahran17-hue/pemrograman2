package P5;

import java.sql.Connection;
import java.sql.DriverManager;

public class Koneksi {
    public static Connection getKoneksi() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/db_mahasiswa",
                "root",
                ""
            );

            System.out.println("Koneksi berhasil");

        } catch (Exception e) {
            System.out.println("Koneksi gagal: " + e.getMessage());
        }
        return conn;
    }
}