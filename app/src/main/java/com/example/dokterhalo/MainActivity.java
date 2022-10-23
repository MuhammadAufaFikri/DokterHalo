package com.example.dokterhalo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;

import android.app.Activity;
import android.os.Bundle;

import com.example.dokterhalo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements FragmentResultListener {

    protected FragmentManager fragmentManager;
    protected ActivityMainBinding binding;


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

        this.fragmentManager.setFragmentResultListener("changePage", this, this);

        this.fragmentManager.setFragmentResultListener("closeApplication", this, this);
    }

    @Override
    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {

    }
}