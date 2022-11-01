package com.example.dokterhalo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.dokterhalo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements FragmentResultListener {

    protected FragmentManager fragmentManager;
    protected ActivityMainBinding binding;
    protected FragmentHome fragmentHome;
    protected FragmentDokter fragmentDokter;
    protected FragmentJanjiTemu fragmentJanjiTemu;
    protected FragmentAddDokter fragmentAddDokter;
    protected FragmentEditDokter fragmentEditDokter;
    protected FragmentListPertemuan fragmentListPertemuan;
    protected FragmentLihatDokter fragmentLihatDokter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityMainBinding.inflate(this.getLayoutInflater());
        setContentView(this.binding.getRoot());

        this.fragmentHome = FragmentHome.newInstance("Home");
        this.fragmentDokter =FragmentDokter.newInstance("Dokter");
        this.fragmentJanjiTemu= FragmentJanjiTemu.newInstance("Pertemuan");
        this.fragmentListPertemuan= FragmentListPertemuan.newInstance("Janji Temu");
        this.fragmentAddDokter= FragmentAddDokter.newInstance("tambah dokter");
        this.fragmentEditDokter=FragmentEditDokter.newInstance("edit dokter");
        this.fragmentLihatDokter=FragmentLihatDokter.newInstance("Lihat");
        this.setSupportActionBar(this.binding.toolbar);

        ActionBarDrawerToggle abdt = new ActionBarDrawerToggle(this, binding.drawerLayout, binding.toolbar, R.string.openDrawer, R.string.closeDrawer);
        this.binding.drawerLayout.addDrawerListener(abdt);
        abdt.syncState();



        this.fragmentManager = this.getSupportFragmentManager();

        this.fragmentManager.beginTransaction().add(R.id.fragment_container, fragmentHome).addToBackStack(null).commit();
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
        if (page == 1) {//home
                ft.replace(R.id.fragment_container, this.fragmentHome).addToBackStack(null);

        } else if (page == 2) {//list dokter

                ft.replace(R.id.fragment_container, this.fragmentDokter).addToBackStack(null);

        }
        else if (page == 3) {// buat pertemuan

                ft.replace(R.id.fragment_container, this.fragmentJanjiTemu).addToBackStack(null);

        }
        else if (page == 4) {// tambah dokter

                ft.replace(R.id.fragment_container, this.fragmentAddDokter).addToBackStack(null);

        }
        else if (page == 5) {// fragment edit dokter

                ft.replace(R.id.fragment_container, this.fragmentEditDokter).addToBackStack(null);

        }
        else if (page == 6) {//list pertemuan

                ft.replace(R.id.fragment_container, this.fragmentListPertemuan).addToBackStack(null);

        }
        else if (page == 7) {//lihat detail dokter

                ft.replace(R.id.fragment_container, this.fragmentLihatDokter).addToBackStack(null);

        }
        ft.commit();
        this.binding.drawerLayout.closeDrawers();

    }

    private void closeApplication(){
        this.moveTaskToBack(true);
        this.finish();
    }
}