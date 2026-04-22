package P6;

public class Mahasiswa {
    private String nim, nama, semester, kelas;

    public Mahasiswa(String nim, String nama, String semester, String kelas) {
        this.nim = nim;
        this.nama = nama;
        this.semester = semester;
        this.kelas = kelas;
    }

    public String getNim() { return nim; }
    public String getNama() { return nama; }
    public String getSemester() { return semester; }
    public String getKelas() { return kelas; }
}