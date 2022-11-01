package com.example.dokterhalo;

import static com.example.dokterhalo.AdapterDokter.list;
import static com.example.dokterhalo.AdapterDokter.list_asli;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import com.example.dokterhalo.databinding.FragmentLihatDokterBinding;

import java.util.List;

public class FragmentLihatDokter extends Fragment implements View.OnClickListener {
    protected FragmentLihatDokterBinding binding;
    protected penyimpanDataDokter storage;
    private int posisi;
    private boolean filter;
    private String nama;
    private static final int SELECT_PICTURE = 1;
    private String str;
    private String photo;
    private String spesialis;
    private String noHp;
    private Bitmap bitmap;
    private String imageFileName;
    private MainPresenter presenter;
    protected AdapterDokter adapter;


    public static FragmentLihatDokter newInstance(String title){
        FragmentLihatDokter fragment = new FragmentLihatDokter();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        this.binding = FragmentLihatDokterBinding.inflate(inflater,container,false);
        View view = this.binding.getRoot();
        this.storage = new penyimpanDataDokter(this.getContext());
        getParentFragmentManager().setFragmentResultListener("detail", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                nama = result.getString("nama");
                str = result.getString("str");
                spesialis = result.getString("spesialis");
                photo = result.getString("photo");
                noHp = result.getString("noHp");
                posisi = result.getInt("list");
                filter = result.getBoolean("isFilter");

                setDetail(nama,spesialis,str);
                setPhoto(photo);
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
            }
        });
        this.adapter = new AdapterDokter(this.getActivity());
        this.storage = new penyimpanDataDokter(this.getContext());

        this.binding.btnPanggil.setOnClickListener(this);
        this.binding.btnBuatJanjididetail.setOnClickListener(this);
        this.binding.btntoEdit.setOnClickListener(this);
        return view;
    }

    private void setPhoto(String photo) {
        if(photo.equalsIgnoreCase("none")){
            this.binding.ivDokterImage.setImageResource(R.drawable.ic_launcher_background);
        }else{
            Bitmap b = this.storage.loadImageFromStorage(photo);
            this.binding.ivDokterImage.setImageBitmap(b);
        }
    }



    private void setDetail(String nama, String spesialis, String str) {
        this.binding.tvNama.setText(nama);
        this.binding.tvSpesailis.setText(spesialis);
        this.binding.tvSTR.setText(str);
    }

    @Override
    public void onClick(View view) {
        if(view == this.binding.btnPanggil){
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", noHp, null));
            startActivity(intent);
        }else if(view == this.binding.btnBuatJanjididetail){
            Bundle result = new Bundle();
            result.putInt("page",3);
            this.getParentFragmentManager().setFragmentResult("changePage",result);
        }else if(view==this.binding.btntoEdit){
            Bundle result = new Bundle();
            result.putInt("page",5);

            result.putString("nama",nama);
            result.putString("str", str);
            result.putString("spesialis", spesialis);
            result.putString("noHp",noHp);
            result.putString("photo",photo);
            result.putInt("list", posisi);
            result.putBoolean("isFilter", filter);
            getParentFragmentManager().setFragmentResult("edit", result);
            this.getParentFragmentManager().setFragmentResult("changePage",result);
        }
    }
}
