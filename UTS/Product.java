package UTS;

public class Product {

    private String nama;
    private String kategori;
    private double harga;

    public Product(String nama, String kategori, double harga) {
        this.nama = nama;
        this.kategori = kategori;
        this.harga = harga;
    }

    public String getNama() {
        return this.nama;
    }

    public String getKategori() {
        return this.kategori;
    }

    public double getHarga() {
        return this.harga;
    }

    @Override
    public String toString() {
        return this.nama + " | " + this.kategori + " | Rp. " + this.harga;
    }
}