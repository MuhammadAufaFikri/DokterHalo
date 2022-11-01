package com.example.dokterhalo;


import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;

import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MainPresenter{
    private List<Dokter> list;
    private List<Dokter> list_Asli;
    private ListOfView ui;

    public MainPresenter(ListOfView lv){
            this.ui = lv;
        }

        public void loadDataDokter(List<String> list, List<String> list_spesialist, List<String> list_str, List<String> list_photo, List<String> list_noHP) {
            this.list = new ArrayList<>();
            this.list_Asli = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Dokter add = new Dokter(list.get(i), list_spesialist.get(i),list_str.get(i), list_photo.get(i), list_noHP.get(i));
            this.list.add(add);
            this.list_Asli.add(add);
        }
        this.ui.updateList(this.list, this.list_Asli);
    }
        @RequiresApi(api = Build.VERSION_CODES.N)
        public void searchData(List<Dokter> list, String searchName,String searchSpesailist){
            List<Dokter> result = list;
            if(searchName!="" && searchSpesailist ==""){
                result = filterDokter(list, x -> x.nama.toLowerCase(Locale.ROOT).contains(searchName.toLowerCase(Locale.ROOT)));
            }
            else if (searchName=="" && searchSpesailist !=""){
                result = filterDokter(list, x -> x.spesialis.toLowerCase(Locale.ROOT).contains(searchSpesailist.toLowerCase(Locale.ROOT)));
            }
            else if (searchName=="" && searchSpesailist ==""){
               // belum beres
            }

            this.ui.SortFilterList(result);
            this.ui.closeSoftKeyboard();
        }

        public void resetFilter(){
            this.ui.loadData();
            this.ui.resetSearchText();
        }


        @RequiresApi(api = Build.VERSION_CODES.N)
        public static List<Dokter> filterDokter(List<Dokter> dokters, Predicate<Dokter> predicate) {
            return dokters.stream().filter(predicate).collect(Collectors.toList());
        }


    interface ListOfView{
    void updateList(List<Dokter> dokters, List<Dokter> origin);
    void SortFilterList(List<Dokter> dokters);
    void storeData(List<Dokter> dokters);
    void resetSearchText();
    void closeSoftKeyboard();
    void loadData();
}
}
