package com.example.github_projek;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.github_projek.database.AkunDao;
import com.example.github_projek.database.AkunDatabase;
import com.example.github_projek.model.db.Akun;

public class DaftarActivity extends AppCompatActivity {

    private EditText daftar_username, daftar_nama, daftar_password, daftar_telepon;
    private Button kembali, daftar;
    private AkunDao akunDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);
        //Memanggil akunDAO
        akunDao = AkunDatabase.getInstance(this).akunDao();

        daftar_username = findViewById(R.id.daftar_username);
        daftar_nama = findViewById(R.id.daftar_nama);
        daftar_password = findViewById(R.id.daftar_password);
        daftar_telepon = findViewById(R.id.daftar_telepon);
        kembali = findViewById(R.id.btn_kembali);
        daftar = findViewById(R.id.btn_simpan);

        //Intent ke Halaman Login
        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DaftarActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //Melakukan proses create di room database
        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Mengambil nilai string
                String usname = daftar_username.getText().toString();
                String name = daftar_nama.getText().toString();
                String pass = daftar_password.getText().toString();
                String tel = daftar_telepon.getText().toString();

                if (usname.trim().length() == 0 || name.trim().length() == 0 || pass.trim().length() == 0 || tel.trim().length() == 0 ) {
                    Toast.makeText(view.getContext(), "Mohon Isi Semua Data", Toast.LENGTH_SHORT).show();
                }else{
                    //Proses Insert Data
                    Akun akun = new Akun(usname, name, pass, tel);
                    Toast.makeText(view.getContext(), "Akun telah berhasil dibuat", Toast.LENGTH_SHORT).show();
                    akunDao.insertData(akun);
                    finish();
                }

            }
        });
    }
}