package com.mycompany.pert17.model;

public class Mobil {
    private String nopol;
    private String merk;
    private String tipe;
    private int tahun;
    private double harga;
    private String status;

    public Mobil() {}

    public Mobil(String nopol, String merk, String tipe, int tahun, double harga, String status) {
        this.nopol = nopol;
        this.merk = merk;
        this.tipe = tipe;
        this.tahun = tahun;
        this.harga = harga;
        this.status = status;
    }

    public String getNopol() { return nopol; }
    public void setNopol(String nopol) { this.nopol = nopol; }

    public String getMerk() { return merk; }
    public void setMerk(String merk) { this.merk = merk; }

    public String getTipe() { return tipe; }
    public void setTipe(String tipe) { this.tipe = tipe; }

    public int getTahun() { return tahun; }
    public void setTahun(int tahun) { this.tahun = tahun; }

    public double getHarga() { return harga; }
    public void setHarga(double harga) { this.harga = harga; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
