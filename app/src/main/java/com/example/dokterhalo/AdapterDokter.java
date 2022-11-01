package com.example.dokterhalo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.dokterhalo.databinding.ItemDokterBinding;

import java.util.ArrayList;
import java.util.List;

public class AdapterDokter extends BaseAdapter {
    private Activity activity;
    public static List<Dokter> list;
    public static List<Dokter> list_asli;
    private ItemDokterBinding binding;

    public AdapterDokter (Activity activity){
        this.activity= activity;
        this.list = new ArrayList<>();
        this.list_asli = new ArrayList<>();
    }
    public void setList(List list){
        this.list=list;
        this.notifyDataSetChanged();
    }
    public void resetList(){
        this.list=this.list_asli;
        this.notifyDataSetChanged();
    }

    public void addLine(List<Dokter> dokterList, List<Dokter> dokterListAsli) {
        this.list = dokterList;
        this.list_asli = dokterListAsli;

        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return this.list.size();
    }

    @Override
    public Object getItem(int i) {
        return this.list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        AdapterDokter.ViewHolder viewHolder;

        if (view == null) {
            binding = ItemDokterBinding.inflate((this.activity.getLayoutInflater()));
            view = binding.getRoot();
            viewHolder = new AdapterDokter.ViewHolder(binding, this, i);
            view.setTag(viewHolder);
        } else {
            viewHolder = (AdapterDokter.ViewHolder) view.getTag();
        }

        Dokter dokterIni = (Dokter) getItem(i);
        viewHolder.setList(dokterIni.getNama(), dokterIni.getSpesialis(),dokterIni.getPhoto());

        return view;
    }

    class ViewHolder {
        private ItemDokterBinding binding;
        private AdapterDokter adapter;
        private int i;
        private penyimpanDataDokter storage;
        public ViewHolder(ItemDokterBinding binding,AdapterDokter adapter, int i) {
            this.binding = binding;
            this.adapter = adapter;
            this.storage = new penyimpanDataDokter(this.adapter.activity.getApplicationContext());
            this.i = i;
        }

        public void setList( String nama, String spesialis, String photo) {
            this.binding.tvNamaDokterList.setText(nama);
            this.binding.tvSpesialisDokterList.setText(spesialis);
            this.setPhoto(photo);

        }
        public void setPhoto(String photo){
            if(photo.equalsIgnoreCase("none")){
                this.binding.ivPhotoDokter.setImageResource(R.drawable.ic_launcher_background);
            }
            else{
                Bitmap b = this.storage.loadImageFromStorage(photo);
                this.binding.ivPhotoDokter.setImageBitmap(b);
            }
        }

    }
}
