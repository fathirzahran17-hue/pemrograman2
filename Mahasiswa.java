package mahasiswa;

public class Mahasiswa {

    // Class Mahasiswa
    static class DataMahasiswa {
        String nama;
        int umur;

        void isiData(String nama, int umur) {
            this.nama = nama;
            this.umur = umur;
        }

        void tampilData() {
            System.out.println("Nama : " + nama);
            System.out.println("Umur : " + umur);
            System.out.println("----------------------");
        }
    }

    public static void main(String[] args) {

        DataMahasiswa mhs1 = new DataMahasiswa();
        DataMahasiswa mhs2 = new DataMahasiswa();

        mhs1.isiData("Andrea Pirlo", 42);
        mhs2.isiData("Cristiano Ronaldo", 35);

        mhs1.tampilData();
        mhs2.tampilData();
    }
}