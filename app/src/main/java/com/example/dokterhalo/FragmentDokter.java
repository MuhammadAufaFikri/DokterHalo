package com.example.dokterhalo;

import static com.example.dokterhalo.AdapterDokter.list;
import static com.example.dokterhalo.AdapterDokter.list_asli;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.dokterhalo.databinding.FragmentListDokterBinding;

import java.util.List;

public class FragmentDokter extends Fragment implements View.OnClickListener, MainPresenter.ListOfView{
    protected FragmentListDokterBinding binding;
    protected AdapterDokter adapter;
    protected penyimpanDataDokter storage;
    private MainPresenter presenter;
    protected boolean isFilter = false;

    public static FragmentDokter newInstance(String title){
        FragmentDokter fragment = new FragmentDokter();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void loadData() {
        List<String> list_nama = this.storage.loadInternal("nama_dokter.txt", 1);
        List<String> list_spesialist = this.storage.loadInternal("spesialisasi_dokter.txt", 1);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.binding = FragmentListDokterBinding.inflate(inflater, container, false);
        View view = this.binding.getRoot();
        this.adapter = new AdapterDokter(this.getActivity());
        this.storage = new penyimpanDataDokter(this.getContext());
        List<String> list_nama = this.storage.loadInternal("nama_dokter.txt", 1);
        List<String> list_spesialist = this.storage.loadInternal("spesialisasi_dokter.txt", 1);
        List<String> list_str = this.storage.loadInternal("str.txt", 1);
        List<String> list_photo = this.storage.loadInternal("photo.txt", 1);
        List<String> list_noHP = this.storage.loadInternal("noHP.txt", 1);
        this.binding.lstDokter.setAdapter(this.adapter);
        this.presenter = new MainPresenter(this);
        this.presenter.loadDataDokter(list_nama, list_spesialist, list_str, list_photo, list_noHP);

        this.binding.lstDokter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Dokter dokterIni = (Dokter) adapter.getItem(i);
                String nama = dokterIni.getNama();
                String spesialis = dokterIni.getSpesialis();
                String photo = dokterIni.getPhoto();
                String str = dokterIni.getStr();
                String noHp = dokterIni.getNohp();
                Bundle result = new Bundle();

                result.putString("nama",nama);
                result.putString("str", str);
                result.putString("spesialis", spesialis);
                result.putString("noHp",noHp);
                result.putString("photo",photo);
                result.putInt("list", i);
                result.putBoolean("isFilter", isFilter);
                result.putInt("page", 7);
                getParentFragmentManager().setFragmentResult("detail", result);
                getParentFragmentManager().setFragmentResult("changePage", result);

            }
        });
        this.binding.btnresetSearch.setOnClickListener(this);
        this.binding.FABdokter.setOnClickListener(this);
        this.binding.btnsearchdokter.setOnClickListener(this);


        return view;
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View view) {
        if(view== this.binding.FABdokter){
            Bundle result = new Bundle();
            result.putInt("page",4);
            this.getParentFragmentManager().setFragmentResult("changePage",result);
        }
        else if (view == this.binding.btnsearchdokter) {
            this.isFilter = true;
            String searchName = this.binding.sortname.getText().toString();
            String searchSpesialist = this.binding.sorspesialist.getText().toString();
            presenter.searchData(list_asli, searchName,searchSpesialist);
        }
        else if (view == this.binding.btnresetSearch) {
            presenter.resetFilter();
        }
    }



    @Override
    public void updateList(List<Dokter> list, List<Dokter> list_asli) {
        this.adapter.addLine(list, list_asli);
    }

    @Override
    public void SortFilterList(List<Dokter> list) {
        this.adapter.setList(list);
    }

    @Override
    public void storeData(List<Dokter> list) {
        for (int i = 0; i < list.size(); i++) {
            if (i == 0) {
                storage.storeInternalNewData(list.get(i).getNama() + "\n", "nama_dokter.txt", 1);
                storage.storeInternalNewData(list.get(i).getSpesialis() + "\n", "spesialisasi_dokter.txt", 1);
                storage.storeInternalNewData(list.get(i).getStr() + "\n", "str.txt", 1);
                storage.storeInternalNewData(list.get(i).getPhoto() + "\n", "photo.txt", 1);
                storage.storeInternalNewData(list.get(i).getNohp() + "\n", "noHP.txt", 1);
            }
            else {
                storage.storeInternal(list.get(i).getNama() + "\n", "nama_dokter.txt", 1);
                storage.storeInternal(list.get(i).getSpesialis() + "\n", "spesialisasi_dokter.txt", 1);
                storage.storeInternal(list.get(i).getStr() + "\n", "str.txt", 1);
                storage.storeInternal(list.get(i).getPhoto() + "\n", "photo.txt", 1);
                storage.storeInternal(list.get(i).getNohp() + "\n", "noHP.txt", 1);
            }
        }
    }

    @Override
    public void resetSearchText() {
        this.binding.sortname.setText("");
        this.binding.sorspesialist.setText("");
        this.adapter.resetList();
    }

    @Override
    public void closeSoftKeyboard() {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }



}