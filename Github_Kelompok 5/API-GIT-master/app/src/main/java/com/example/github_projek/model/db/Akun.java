package com.example.github_projek.model.db;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "akuns")
public class Akun implements Parcelable  {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Id")
    private int id;
    @ColumnInfo(name = "Username")
    private String username;
    @ColumnInfo(name = "Nama")
    private String nama;
    @ColumnInfo(name = "Password")
    private String password;
    @ColumnInfo(name = "Telepon")
    private String telepon;

    @Ignore
    public Akun(int id, String username, String nama, String password, String telepon) {
        this.id = id;
        this.username = username;
        this.nama = nama;
        this.password = password;
        this.telepon = telepon;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }


    public Akun( String username, String nama, String password, String telepon) {
        this.username = username;
        this.nama = nama;
        this.password = password;
        this.telepon = telepon;

    }

    protected Akun(Parcel in) {
        id = in.readInt();
        username = in.readString();
        nama = in.readString();
        password = in.readString();
        telepon = in.readString();
    }

    public static final Creator<Akun> CREATOR = new Creator<Akun>() {
        @Override
        public Akun createFromParcel(Parcel in) {
            return new Akun(in);
        }

        @Override
        public Akun[] newArray(int size) {
            return new Akun[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(username);
        parcel.writeString(nama);
        parcel.writeString(password);
        parcel.writeString(telepon);
    }
}
