package com.example.dokterhalo;

import android.graphics.Bitmap;

import java.util.List;

public class EditDokterPresenter {
    private List<Dokter> list;
    private List<Dokter> list_asli;

    private EditDokterInterface ui;
    private penyimpanDataDokter storage;

    public EditDokterPresenter(EditDokterInterface editDokterInterface, penyimpanDataDokter storage){
        this.ui = editDokterInterface;
        this.storage = storage;
    }

    public void editDokter(boolean filter,int posisi, String str, String newNama, String spesialis, String newstr, String photo, Bitmap bitmap, String imageFileName, List<Dokter> list, String photo_asli, String nohp) {
        this.list = list;
        Dokter dokter = null;
        if(filter == false){
            dokter = (Dokter) this.list.get(posisi);
        }else{
            for(int i=0; i<this.list.size(); i++){
                if(this.list.get(i).getStr().equals(str)){
                    dokter = (Dokter) this.list.get(i);
                    break;
                }
            }
        }
        if (dokter.getPhoto() == "none"){
            dokter.setPhoto("none");
        }else if(bitmap!=null){
            if(imageFileName==null){
                dokter.setPhoto(photo_asli);
            }
            else {
                String imgPath = this.storage.saveToInternalStorage(bitmap, imageFileName);
                dokter.setPhoto(imgPath + "@" + imageFileName);
            }
        }
        dokter.setNama(newNama);
        dokter.setSpesialis(spesialis);
        dokter.setNohp(nohp);
        dokter.setStr(newstr);
        this.ui.storeUpdatedData(this.list);
        this.ui.closeSoftKeyBoard();
    }

    public void deleteDokter(int posisi, boolean filter, List<Dokter> list_asli) {
        if(filter == false){
            this.list.remove(posisi);
            this.list_asli.remove(posisi);
        }else{
            String nama_hps = list.remove(posisi).getNama();
            for(int i=0; i<list_asli.size(); i++){
                if(this.list_asli.get(i).getNama().equals(nama_hps)){
                    this.list_asli.remove(i);
                    break;
                }
            }
            this.list = this.list_asli;
        }
        this.ui.storeUpdatedData(this.list);
        this.ui.updateList(this.list, this.list_asli);
    }


    interface EditDokterInterface{
        void closeSoftKeyBoard();
        void storeUpdatedData(List<Dokter> dokters);

        void updateList(List<Dokter> list, List<Dokter> list_asli);

    }
}
