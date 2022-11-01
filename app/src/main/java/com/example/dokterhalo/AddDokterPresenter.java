package com.example.dokterhalo;

public class AddDokterPresenter {
    private TambahDokterInterface ui;

    public AddDokterPresenter(TambahDokterInterface tambahDokterInterface){
        this.ui = tambahDokterInterface;
    }

    public void addDokter(String nama, String spesialis,String str, String photo, String nohp){
        this.ui.storeNewData(nama,spesialis,str,photo,nohp);
        this.ui.resetAddForm();
    }


    interface TambahDokterInterface{
        void resetAddForm();
        void storeNewData(String nama, String spesialis,String str, String photo, String nohp);
    }
}
