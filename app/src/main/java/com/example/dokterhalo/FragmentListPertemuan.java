package com.example.dokterhalo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.dokterhalo.databinding.FragmentAdddokterBinding;
import com.example.dokterhalo.databinding.FragmentListPertemuanBinding;

public class FragmentListPertemuan extends Fragment {
    protected FragmentListPertemuanBinding binding;
    public static FragmentListPertemuan newInstance(String title){
        FragmentListPertemuan fragment = new FragmentListPertemuan();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        this.binding = FragmentListPertemuanBinding.inflate(inflater,container,false);
        View view = this.binding.getRoot();
        return view;
    }
}
