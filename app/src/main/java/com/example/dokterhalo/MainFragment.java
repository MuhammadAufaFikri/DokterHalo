package com.example.dokterhalo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.dokterhalo.databinding.FragmentMainBinding;

public class MainFragment extends Fragment implements View.OnClickListener {
    protected FragmentMainBinding binding;
    public static MainFragment newInstance(String title){
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        this.binding = FragmentMainBinding.inflate(inflater,container,false);
        this.binding.btnMain.setOnClickListener(this);
        View view = this.binding.getRoot();
        return view;
    }
    @Override
    public void onClick(View view) {
        Bundle result = new Bundle();
        result.putInt("page",2);
        this.getParentFragmentManager().setFragmentResult("changePage",result);
    }
}
