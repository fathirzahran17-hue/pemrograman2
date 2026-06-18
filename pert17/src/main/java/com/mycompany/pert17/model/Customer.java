package com.mycompany.pert17.model;

public class Customer {
    private String nik;
    private String nama;
    private String alamat;
    private String telp;

    public Customer() {}

    public Customer(String nik, String nama, String alamat, String telp) {
        this.nik = nik;
        this.nama = nama;
        this.alamat = alamat;
        this.telp = telp;
    }

    public String getNik() { return nik; }
    public void setNik(String nik) { this.nik = nik; }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public String getAlamat() { return alamat; }
    public void setAlamat(String alamat) { this.alamat = alamat; }

    public String getTelp() { return telp; }
    public void setTelp(String telp) { this.telp = telp; }
}
