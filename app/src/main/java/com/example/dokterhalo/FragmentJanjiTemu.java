package com.example.dokterhalo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.dokterhalo.databinding.FragmentBuatPertemuanBinding;
import com.example.dokterhalo.databinding.FragmentListDokterBinding;
import com.example.dokterhalo.databinding.FragmentListPertemuanBinding;

import java.util.List;

public class FragmentJanjiTemu extends Fragment {
    protected FragmentBuatPertemuanBinding binding;
    public static FragmentJanjiTemu newInstance(String title){
        FragmentJanjiTemu fragment = new FragmentJanjiTemu();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.binding = FragmentBuatPertemuanBinding.inflate(inflater, container, false);
        View view = this.binding.getRoot();

        return view;
    }

}
