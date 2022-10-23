package com.example.dokterhalo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.fragment.app.Fragment;

import com.example.dokterhalo.databinding.FragmentLeftBinding;

public class LeftFragment extends Fragment implements AdapterView.OnItemClickListener{
    protected adapterLeftFragment adapter;
    protected FragmentLeftBinding binding;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_left, container, false);

        String[] menu = {"Home", "Daftar", "Watching List", "History", "Pengaturan", "Exit"};
//        this.adapterDokter = new MenuAdapter(this);
//        this.adapterDokter.add(menu);
        this.adapter = new adapterLeftFragment(this.getActivity());
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
        }else if(adapter.getItem(i).equals("Daftar")){
            result.putInt("page", 2);
            getParentFragmentManager().setFragmentResult("changePage", result);
        }else{
            getParentFragmentManager().setFragmentResult("closeApplication", result);
        }
    }
}
