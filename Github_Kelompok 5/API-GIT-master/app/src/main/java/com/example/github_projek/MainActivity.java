package com.example.github_projek;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.github_projek.database.AkunDao;
import com.example.github_projek.database.AkunDatabase;
import com.example.github_projek.model.db.Akun;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText user, pass;
    Button login, register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Membuat file sharedpreferences baru dengan identifikasinya menurut nama "SAVED_LOGIN"
        SharedPreferences getPreferences = getSharedPreferences("SAVED_LOGIN", MODE_PRIVATE);

        //Mengambil nilai boolean pada preferensi
        Boolean check = getPreferences.getBoolean("LOGGED",false);
        String username = getPreferences.getString("USERNAME", "DEFAULT");

        //Mengecek jika status sudah login belum melakukan logout sebelumnya maka akan otomatis masuk
        if (check){
            Intent intent = new Intent(MainActivity.this , NavbarActivity.class);
            MainActivity.this.startActivity(intent);
            finish();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = findViewById(R.id.username);
        pass = findViewById(R.id.password);
        login = findViewById(R.id.btn_login);
        register = findViewById(R.id.btn_register);

        //Intent ke halaman register akun
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainActivity.this,DaftarActivity.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Mendapat username & password dari EditText
                String username = user.getText().toString();
                String password = pass.getText().toString();



                //Mengecek username dan password terisi ditampilan atau tidak
                if(username.trim().length() == 0 || password.trim().length() == 0 ){
                    //JIka Username Kosong
                    Toast.makeText(getApplicationContext(),"Mohon Isi Username dan Password", Toast.LENGTH_LONG).show();
                }else if(username.trim().length() > 0 && password.trim().length() > 0){
                    //Pemanggilan database dan akunDAO
                    AkunDatabase akunDatabase = AkunDatabase.getInstance(getApplicationContext());
                    AkunDao akunDao = akunDatabase.akunDao();

                    Akun akun = akunDao.login(username,password);

                    //Jika akun tidak terdaftar di database
                    if (akun == null){
                        Toast.makeText(getApplicationContext(), "Username dan Password yang Anda Masukkan Salah", Toast.LENGTH_SHORT).show();
                    }else{
                        SharedPreferences sharedPreferences = getSharedPreferences("SAVED_LOGIN", MODE_PRIVATE);

                        //Membuat editor untuk bisa mengubah nilai dalam objek Shared Preference
                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        editor.putBoolean("LOGGED",true);
                        editor.putString("USERNAME",username);
                        editor.apply();
                        //selesai melakukan perubahan

                        Intent intent = new Intent(getApplicationContext(), NavbarActivity.class);
                        Toast.makeText(getApplicationContext(),"Selamat Datang " + username, Toast.LENGTH_LONG).show();
                        MainActivity.this.startActivity(intent);
                        finish();
                    }
                }

            }
        });

    }
}