CREATE DATABASE IF NOT EXISTS rentcar_fathir;
USE rentcar_fathir;

CREATE TABLE IF NOT EXISTS mobil (
    nopol VARCHAR(20) PRIMARY KEY,
    merk VARCHAR(50) NOT NULL,
    tipe VARCHAR(50) NOT NULL,
    tahun INT NOT NULL,
    harga DOUBLE NOT NULL,
    status VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS customer (
    nik VARCHAR(20) PRIMARY KEY,
    nama VARCHAR(100) NOT NULL,
    alamat TEXT NOT NULL,
    telp VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS transaksi (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nik_customer VARCHAR(20) NOT NULL,
    nopol_mobil VARCHAR(20) NOT NULL,
    tgl_sewa DATE NOT NULL,
    tgl_kembali DATE,
    total_harga DOUBLE NOT NULL,
    status VARCHAR(20) NOT NULL,
    FOREIGN KEY (nik_customer) REFERENCES customer(nik) ON DELETE CASCADE,
    FOREIGN KEY (nopol_mobil) REFERENCES mobil(nopol) ON DELETE CASCADE
);

-- Insert dummy data
INSERT IGNORE INTO mobil (nopol, merk, tipe, tahun, harga, status) VALUES 
('B 1234 ABC', 'Toyota', 'Avanza', 2022, 300000, 'Tersedia'),
('D 5678 DEF', 'Honda', 'Brio', 2021, 250000, 'Tersedia');

INSERT IGNORE INTO customer (nik, nama, alamat, telp) VALUES 
('3201234567890001', 'John Doe', 'Jl. Sudirman No 1', '08123456789');
