package com.example.dokterhalo;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.dokterhalo.databinding.ListButtonBinding;

import java.util.ArrayList;

public class AdapterNavigation extends BaseAdapter {
    private Activity activity;
    protected ArrayList<String> list;
    private ListButtonBinding binding;

    public AdapterNavigation(Activity activity) {
        this.activity = activity;
        this.list = new ArrayList<>();
    }

    public void initList(String[] list){
        for(String str : list) {
            this.list.add(str);
        }
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
        ViewHolder vh;

        if(view == null){
            binding = ListButtonBinding.inflate((this.activity.getLayoutInflater()));
            view = binding.getRoot();
            vh = new ViewHolder(binding, this, i);
            view.setTag(vh);
        }else{
             vh= (ViewHolder) view.getTag();
        }
        return view;
    }
    class ViewHolder{
        private ListButtonBinding binding;
        private AdapterNavigation adapter;
        private int i;

        public ViewHolder(ListButtonBinding binding, AdapterNavigation adapter, int i){
            this.binding = binding;
            this.adapter = adapter;
            this.i = i;
            this.binding.tvDrawer.setText((String)getItem(i));
        }
    }
}
