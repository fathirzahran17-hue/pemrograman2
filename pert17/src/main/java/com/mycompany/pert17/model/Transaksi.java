package com.mycompany.pert17.model;

import java.sql.Date;

public class Transaksi {
    private int id;
    private String nikCustomer;
    private String nopolMobil;
    private Date tglSewa;
    private Date tglKembali;
    private double totalHarga;
    private String status;

    public Transaksi() {}

    public Transaksi(int id, String nikCustomer, String nopolMobil, Date tglSewa, Date tglKembali, double totalHarga, String status) {
        this.id = id;
        this.nikCustomer = nikCustomer;
        this.nopolMobil = nopolMobil;
        this.tglSewa = tglSewa;
        this.tglKembali = tglKembali;
        this.totalHarga = totalHarga;
        this.status = status;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNikCustomer() { return nikCustomer; }
    public void setNikCustomer(String nikCustomer) { this.nikCustomer = nikCustomer; }

    public String getNopolMobil() { return nopolMobil; }
    public void setNopolMobil(String nopolMobil) { this.nopolMobil = nopolMobil; }

    public Date getTglSewa() { return tglSewa; }
    public void setTglSewa(Date tglSewa) { this.tglSewa = tglSewa; }

    public Date getTglKembali() { return tglKembali; }
    public void setTglKembali(Date tglKembali) { this.tglKembali = tglKembali; }

    public double getTotalHarga() { return totalHarga; }
    public void setTotalHarga(double totalHarga) { this.totalHarga = totalHarga; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
