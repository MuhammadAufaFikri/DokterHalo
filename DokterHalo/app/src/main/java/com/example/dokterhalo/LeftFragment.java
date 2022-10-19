package com.example.dokterhalo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.appcompat.view.menu.MenuAdapter;

public class LeftFragment {
    protected MenuAdapter adapter;
    protected ListView lv;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_left, container, false);

        String[] menu = {"Home", "Daftar", "Watching List", "History", "Pengaturan", "Exit"};
//        this.adapter = new MenuAdapter(this);
//        this.adapter.add(menu);
        this.lv = view.findViewById(R.id.lv_menu);
        this.lv.setAdapter(this.adapter);

        return view;
    }
}
