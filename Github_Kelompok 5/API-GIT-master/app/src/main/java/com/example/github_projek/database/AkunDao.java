package com.example.github_projek.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.github_projek.model.api.User;
import com.example.github_projek.model.db.Akun;

import java.util.List;

@Dao
public interface AkunDao {
    @Query("SELECT*FROM akuns")
    List<Akun> getAllData();

    @Insert
    void insertData(Akun akuns);

    @Update
    void updateData(Akun akuns);

    @Delete
    void deleteData(Akun akuns);

    @Query("DELETE FROM akuns WHERE username= :username")
    void deleteSingleData(String username);

    @Query("SELECT * FROM akuns WHERE username = :username and password = :password")
    Akun login(String username, String password);


    @Query("SELECT * FROM akuns WHERE username = :username")
    Akun checkUser(String username);
}
