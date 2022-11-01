package com.example.dokterhalo;

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

import androidx.fragment.app.Fragment;

import com.example.dokterhalo.databinding.FragmentAdddokterBinding;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FragmentAddDokter extends Fragment implements View.OnClickListener, AddDokterPresenter.TambahDokterInterface{
    protected FragmentAdddokterBinding binding;
    protected penyimpanDataDokter storage;
    private static final int SELECT_PICTURE = 1;
    private String imageFileName;
    private Bitmap bitmap;
    private AddDokterPresenter presenter;
    public static FragmentAddDokter newInstance(String title){
        FragmentAddDokter fragment = new FragmentAddDokter();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        this.binding = FragmentAdddokterBinding.inflate(inflater,container,false);
        View view = this.binding.getRoot();
        this.presenter = new AddDokterPresenter(this);
        this.storage = new penyimpanDataDokter(this.getContext());
        this.binding.btnSave.setOnClickListener(this);
        this.binding.btnChangeImage.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        if(view == this.binding.btnSave){
            String nama = this.binding.etNama.getText().toString();
            String spesialis = this.binding.etSpesailis.getText().toString();
            String str = this.binding.etSTR.getText().toString();
            String nohp = this.binding.etNoHp.getText().toString();
            String imgPath = "none";
            if(bitmap!=null){
                imgPath = this.storage.saveToInternalStorage(bitmap, imageFileName) +"@"+imageFileName ;
                bitmap=null;
            }
            this.presenter.addDokter(nama,spesialis,str,imgPath,nohp);
            Bundle result = new Bundle();
            result.putInt("page", 2);
            this.getParentFragmentManager().setFragmentResult("changePage", result);
        }
        else if(view == this.binding.btnChangeImage){
            imageChooser();
        }
    }

    @Override
    public void resetAddForm() {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
        this.binding.etNama.setText("");
        this.binding.etSpesailis.setText("");
        this.binding.etSTR.setText("");
        this.binding.etNoHp.setText("");
        this.binding.ivAddImage.setImageResource(R.drawable.ic_launcher_background);
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
    public void storeNewData(String nama, String spesialis, String str, String photo, String nohp) {
        storage.storeInternal(this.binding.etNama.getText().toString() + "\n", "nama_dokter.txt", 1);
        storage.storeInternal(this.binding.etSpesailis.getText().toString() + "\n", "spesialisasi_dokter.txt", 1);
        storage.storeInternal(this.binding.etSTR.getText().toString() + "\n", "str.txt", 1);
        storage.storeInternal(photo + "\n", "photo.txt", 1);
        storage.storeInternal(this.binding.etNoHp.getText().toString() + "\n", "noHP.txt", 1);
    }
}
