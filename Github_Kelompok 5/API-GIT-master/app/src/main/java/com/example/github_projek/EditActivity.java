package com.example.github_projek;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.github_projek.database.AkunDao;
import com.example.github_projek.database.AkunDatabase;
import com.example.github_projek.model.db.Akun;

public class EditActivity extends AppCompatActivity{
    Button kembali, simpan;

    private EditText Edit_Username, Edit_Nama, Edit_Password, Edit_Telepon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        SharedPreferences getPreferences = getSharedPreferences("SAVED_LOGIN", Context.MODE_PRIVATE);
        String username = getPreferences.getString("USERNAME", "DEFAULT");

        //Pemanggilan database dan akunDAO untuk mengecek akun
        AkunDatabase akunDatabase = AkunDatabase.getInstance(this);
        AkunDao akunDAO = akunDatabase.akunDao();
        Akun akun = akunDAO.checkUser(username);

        Edit_Username = findViewById(R.id.et_username);
        Edit_Nama = findViewById(R.id.et_nama);
        Edit_Password = findViewById(R.id.et_password);
        Edit_Telepon = findViewById(R.id.et_phone);
        simpan = findViewById(R.id.edit_simpan);
        kembali = findViewById(R.id.edit_kembali);

        //untuk menampilkan data ke halaman edit akun
        Edit_Username.setText(akun.getUsername());
        Edit_Nama.setText(akun.getNama());
        Edit_Password.setText(akun.getPassword());
        Edit_Telepon.setText(akun.getTelepon());

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!Edit_Username.getText().toString().isEmpty() && !Edit_Nama.getText().toString().isEmpty() && !Edit_Password.getText().toString().isEmpty() && !Edit_Telepon.getText().toString().isEmpty()) {
                    //Akun yang diedit dicek
                    Akun check = akunDAO.checkUser(Edit_Username.getText().toString());
                        //jika nama username sama dengan sebelumnya
                        if (akun.getUsername().equals(Edit_Username.getText().toString())) {
                            String usname = Edit_Username.getText().toString();
                            String name = Edit_Nama.getText().toString();
                            String pass = Edit_Password.getText().toString();
                            String tel = Edit_Telepon.getText().toString();

                            Akun databaru = new Akun(akun.getId(), usname, name, pass, tel);

                            //proses update data
                            akunDAO.updateData(databaru);

                            Intent in = new Intent(EditActivity.this, NavbarActivity.class);
                            Toast.makeText(view.getContext(), "Berhasil Melakukan Perubahan", Toast.LENGTH_SHORT).show();
                            startActivity(in);


                        } else {
                            if (check != null) {
                                Toast.makeText(view.getContext(), "Username telah ada", Toast.LENGTH_SHORT).show();
                            } else {

                                String usname = Edit_Username.getText().toString();
                                String name = Edit_Nama.getText().toString();
                                String pass = Edit_Password.getText().toString();
                                String tel = Edit_Telepon.getText().toString();

                                Akun databaru = new Akun(akun.getId(), usname, name, pass, tel);

                                //update data
                                akunDAO.updateData(databaru);

                                //melakukan edit pada preference
                                SharedPreferences.Editor editor = getPreferences.edit();
                                editor.putString("USERNAME",Edit_Username.getText().toString());
                                editor.apply();
                                //edit selesai

                                Intent intent = new Intent(EditActivity.this, NavbarActivity.class);
                                Toast.makeText(view.getContext(), "Berhasil Melakukan Perubahan", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(intent);
                            }
                        }

                }else{
                    Toast.makeText(view.getContext(), "Tidak Boleh Ada yang Kosong", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //intent ke halaman profil
        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditActivity.this,ProfilFragment.class);
                startActivity(intent);
            }
        });
    }


}