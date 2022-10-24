package com.example.dokterhalo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.os.Bundle;

import com.example.dokterhalo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements FragmentResultListener {

    protected FragmentManager fragmentManager;
    protected ActivityMainBinding binding;
    protected MainFragment mf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityMainBinding.inflate(this.getLayoutInflater());
        setContentView(this.binding.getRoot());


        this.setSupportActionBar(this.binding.toolbar);

        ActionBarDrawerToggle abdt = new ActionBarDrawerToggle(this, binding.drawerLayout,

                binding.toolbar, R.string.openDrawer, R.string.closeDrawer);
        this.binding.drawerLayout.addDrawerListener(abdt);
        abdt.syncState();


        this.fragmentManager = this.getSupportFragmentManager();

        this.fragmentManager.beginTransaction().add(R.id.fragment_container, mf).addToBackStack(null).commit();

        this.fragmentManager.setFragmentResultListener("changePage", this, this);

        this.fragmentManager.setFragmentResultListener("closeApplication", this, this);
    }

    @Override
    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
        if(requestKey.equals("changePage")){
            int page = result.getInt("page");
            changePage(page);
        }else if(requestKey.equals("closeApplication")){
            closeApplication();
        }
    }
    private void changePage(int page) {
        FragmentTransaction ft = this.fragmentManager.beginTransaction();
        if (page == 1) {
            if (this.mf.isAdded()) {
                ft.show(this.mf);
            } else {
                ft.add(R.id.fragment_container, this.mf);
            }

        } else if (page == 2) {


        }
        ft.commit();
        this.binding.drawerLayout.closeDrawers();

    }

    private void closeApplication(){
        this.moveTaskToBack(true);
        this.finish();
    }
}