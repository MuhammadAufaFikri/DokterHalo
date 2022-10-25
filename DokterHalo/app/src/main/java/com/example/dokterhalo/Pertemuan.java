package com.example.dokterhalo;

public class Pertemuan {
    private String nama;
    private String dokter;
    private String keluhan;
    private String tanggal;
    private String waktu;

    public Pertemuan(String nama, String dokter, String keluhan, String tanggal, String waktu){
        this.nama = nama;
        this.dokter = dokter;
        this.keluhan = keluhan;
        this.tanggal = tanggal;
        this.waktu = waktu;
    }

    public String getNama() {
        return nama;
    }

    public String getDokter() {return dokter;}

    public String getKeluhan() {
        return keluhan;
    }

    public String getTanggal() {return tanggal;}

    public String getWaktu() {return waktu;}
}
