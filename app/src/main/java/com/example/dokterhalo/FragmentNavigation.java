package com.example.dokterhalo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.fragment.app.Fragment;

import com.example.dokterhalo.databinding.FragmentNavigationBinding;

public class FragmentNavigation extends Fragment implements AdapterView.OnItemClickListener{
    protected AdapterNavigation adapter;
    protected FragmentNavigationBinding binding;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        this.binding= FragmentNavigationBinding.inflate(inflater,container,false );
        View view=this.binding.getRoot();
        String[] menu = {"Home", "Pertemuan", "Dokter", "Pengaturan", "Exit"};
//        this.adapterDokter = new MenuAdapter(this);
//        this.adapterDokter.add(menu);
        this.adapter = new AdapterNavigation(this.getActivity());
        this.adapter.initList(menu);
        this.binding.lstButton.setAdapter(adapter);
        this.binding.lstButton.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Bundle result = new Bundle();
        if(adapter.getItem(i).equals("Home")){
            result.putInt("page", 1);
            getParentFragmentManager().setFragmentResult("changePage", result);
        }else if(adapter.getItem(i).equals("Pertemuan")){
            result.putInt("page", 6);
            getParentFragmentManager().setFragmentResult("changePage", result);
        }else if(adapter.getItem(i).equals("Dokter")){
            result.putInt("page", 2);
            getParentFragmentManager().setFragmentResult("changePage", result);
        }else if(adapter.getItem(i).equals("Pengaturan")){
            result.putInt("page", 8);
            getParentFragmentManager().setFragmentResult("changePage", result);
        }else{
            getParentFragmentManager().setFragmentResult("closeApplication", result);
        }
    }

}
