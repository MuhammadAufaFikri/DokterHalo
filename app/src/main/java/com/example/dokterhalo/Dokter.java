package com.example.dokterhalo;

public class Dokter {
    protected String nama;
    protected String spesialis;
    protected String str;
    protected String photo;
    protected String nohp;

    public Dokter(String nama, String spesialis,String str, String photo, String nohp){
        this.nama = nama;
        this.spesialis = spesialis;
        this.str =str;
        this.photo = photo;
        this.nohp = nohp;
    }

    public void setNama(String nama){
        this.nama = nama;
    }

    public void setSpesialis(String spesialis){
        this.spesialis = spesialis;
    }

    public void setStr(String str){
        this.str = str;
    }

    public void setPhoto(String photo){
        this.photo = photo;
    }

    public void setNohp(String nohp){
        this.nohp = nohp;
    }


    public String getNama(){
        return this.nama;
    }

    public String getSpesialis(){
        return this.spesialis;
    }

    public String getStr(){
        return this.str;
    }

    public String getPhoto(){
        return this.photo;
    }

    public String getNohp(){
        return this.nohp;
    }

}
