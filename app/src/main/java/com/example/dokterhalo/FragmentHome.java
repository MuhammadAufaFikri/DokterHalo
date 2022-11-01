package com.example.dokterhalo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.dokterhalo.databinding.FragmentHomeBinding;

public class FragmentHome extends Fragment implements View.OnClickListener {
    protected FragmentHomeBinding binding;
    public static FragmentHome newInstance(String title){
        FragmentHome fragment = new FragmentHome();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        this.binding = FragmentHomeBinding.inflate(inflater,container,false);
        this.binding.btnMain.setOnClickListener(this);
        View view = this.binding.getRoot();
        return view;
    }
    @Override
    public void onClick(View view) {
        if(this.binding.btnMain==view){
            Bundle result = new Bundle();
            result.putInt("page",3);
            this.getParentFragmentManager().setFragmentResult("changePage",result);
        }

    }
}
