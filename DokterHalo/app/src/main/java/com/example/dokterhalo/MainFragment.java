package com.example.dokterhalo;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.dokterhalo.databinding.FragmentMainBinding;

public class MainFragment extends Fragment implements View.OnClickListener{
    private FragmentMainBinding binding;
    private FragmentTransaction transaction;
    private FragmentManager fragmentManager;
    public static MainFragment newInstance(){
        MainFragment fragment = new MainFragment();
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        this.binding = FragmentMainBinding.inflate(inflater, container, false);
        return this.binding.getRoot();

    }

    @Override
    public void onClick(View v) {

    }
}

