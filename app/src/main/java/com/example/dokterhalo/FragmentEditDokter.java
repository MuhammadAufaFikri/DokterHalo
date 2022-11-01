package com.example.dokterhalo;


import static com.example.dokterhalo.AdapterDokter.list_asli;
import static android.app.Activity.RESULT_OK;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import com.example.dokterhalo.databinding.FragmentEditDokterBinding;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class FragmentEditDokter extends Fragment implements View.OnClickListener, EditDokterPresenter.EditDokterInterface {
    protected FragmentEditDokterBinding binding;
    protected penyimpanDataDokter storage;
    private static final int SELECT_PICTURE = 1;
    private String imageFileName;
    private Bitmap bitmap;
    private EditDokterPresenter presenter;
    private int posisi;
    private boolean filter;
    private String nama;
    private String str;
    private String photo;
    private String spesialis;
    private String noHp;
    protected AdapterDokter adapter;
    public static FragmentEditDokter newInstance(String title){
        FragmentEditDokter fragment = new FragmentEditDokter();
        Bundle args = new Bundle();
        args.putString("title_edit", title);
        fragment.setArguments(args);
        return fragment;
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        this.binding = FragmentEditDokterBinding.inflate(inflater,container,false);
        View view = this.binding.getRoot();
        this.storage = new penyimpanDataDokter(this.getContext());
        this.presenter = new EditDokterPresenter(this, this.storage);
        this.imageFileName = null;
        getParentFragmentManager().setFragmentResultListener("edit", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                nama = result.getString("nama");
                str = result.getString("str");
                spesialis = result.getString("spesialis");
                photo = result.getString("photo");
                noHp = result.getString("noHp");
                posisi = result.getInt("list");
                filter = result.getBoolean("isFilter");

                setDetail(nama,spesialis,str,noHp);
                setPhoto(photo);
                getParentFragmentManager().setFragmentResult("detail", result);
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
            }
        });
        this.binding.btnSave.setOnClickListener(this);
        this.binding.btnChangeImage.setOnClickListener(this);
        this.binding.btnDel.setOnClickListener(this);
        return view;
    }

    private void setPhoto(String photo) {
        if(photo.equalsIgnoreCase("none")){
            this.binding.ivAddImage.setImageResource(R.drawable.ic_launcher_background);
        }else{
            Bitmap b = this.storage.loadImageFromStorage(photo);
            this.binding.ivAddImage.setImageBitmap(b);
        }
    }

    private void setDetail(String nama, String spesialis, String str, String noHp) {
        this.binding.etNama.setText(nama);
        this.binding.etSpesailis.setText(spesialis);
        this.binding.etSTR.setText(str);
        this.binding.etNoHp.setText(noHp);
    }

    @Override
    public void onClick(View view) {
        if(view==this.binding.btnChangeImage){
            imageChooser();
        }else if(view== this.binding.btnSave){
            String imgPath = "none";
            String newNama = binding.etNama.getText().toString();
            String spesialis = binding.etSpesailis.getText().toString();
            String str = this.binding.etSTR.getText().toString();
            String nohp = this.binding.etNoHp.getText().toString();
            if(bitmap!=null){
                imgPath= this.storage.saveToInternalStorage(bitmap, imageFileName)+"@"+imageFileName;
            }
            this.presenter.editDokter(this.filter, this.posisi, this.nama, newNama, spesialis,str, imgPath, this.bitmap, this.imageFileName, AdapterDokter.list_asli, photo,nohp);
            Bundle result = new Bundle();
            result.putInt("page", 2);
            getParentFragmentManager().setFragmentResult("changePage", result);
        }else if(view== this.binding.btnDel){
            presenter.deleteDokter(this.posisi, this.filter, list_asli);
            Bundle result = new Bundle();
            result.putInt("page", 2);
            getParentFragmentManager().setFragmentResult("changePage", result);
        }
    }

    private void imageChooser() {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();

                if (null != selectedImageUri) {
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(this.getContext().getContentResolver(), selectedImageUri);
                        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                        imageFileName = "photo_" + timeStamp +".jpg";
                        this.binding.ivAddImage.setImageBitmap(bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public void closeSoftKeyBoard() {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }

    @Override
    public void storeUpdatedData(List<Dokter> dokters) {
        for(int i=0; i<dokters.size(); i++){
            if(i == 0) {
                storage.storeInternalNewData(dokters.get(i).getNama() + "\n", "nama_dokter.txt", 1);
                storage.storeInternalNewData(dokters.get(i).getSpesialis() + "\n", "spesialisasi_dokter.txt", 1);
                storage.storeInternalNewData(dokters.get(i).getStr()  + "\n", "str.txt", 1);
                storage.storeInternalNewData(dokters.get(i).getPhoto()  + "\n", "photo.txt", 1);
                storage.storeInternalNewData(dokters.get(i).getNohp()  + "\n", "noHP.txt", 1);
            }else{
                storage.storeInternal(dokters.get(i).getNama() + "\n", "nama_dokter.txt", 1);
                storage.storeInternal(dokters.get(i).getSpesialis() + "\n", "spesialisasi_dokter.txt", 1);
                storage.storeInternal(dokters.get(i).getStr()  + "\n", "str.txt", 1);
                storage.storeInternal(dokters.get(i).getPhoto()  + "\n", "photo.txt", 1);
                storage.storeInternal(dokters.get(i).getNohp()  + "\n", "noHP.txt", 1);
            }
        }
    }


    @Override
    public void updateList(List<Dokter> list, List<Dokter> list_asli) {
        this.adapter.addLine(list, list_asli);
    }

}
