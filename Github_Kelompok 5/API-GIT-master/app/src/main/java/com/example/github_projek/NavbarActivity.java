package com.example.github_projek;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class NavbarActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navbar);

        BottomNavigationView navigasi = findViewById(R.id.bottom_nav);
        navigasi.setOnNavigationItemSelectedListener(this);

        prosesFragment(new HomeFragment());
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_home:
                prosesFragment(new HomeFragment());
                break;

            case R.id.menu_profil:
                prosesFragment(new ProfilFragment());
                break;

        } return true;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    private boolean prosesFragment(Fragment selectedFragment){
        if (selectedFragment != null){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,selectedFragment).commit();
            return true;
        }
        else{
            return false;
        }
    }

}