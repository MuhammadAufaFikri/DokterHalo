package com.example.dokterhalo;

public class Dokter {
    private String nama;
    private String spesialis;
    private int photo;

    public Dokter(String nama, String spesialis, int photo){
        this.nama = nama;
        this.spesialis = spesialis;
        this.photo = photo;
    }

    public Dokter(String nama, String spesialis) {
        this.nama = nama;
        this.spesialis = spesialis;
        this.photo = R.drawable.ic_launcher_person_foreground;
    }

    public String getNama() {
        return nama;
    }

    public String getSpesialis() {
        return spesialis;
    }

    public int getPhoto() {
        return photo;
    }
}