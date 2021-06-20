package com.example.github_projek.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.github_projek.model.db.Akun;

@Database(entities = Akun.class, version = 1)
public abstract class AkunDatabase extends RoomDatabase {
    private static final String DB_NAME = "db_akun";
    private static AkunDatabase instance;
    public abstract AkunDao akunDao();

    public static AkunDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AkunDatabase.class, DB_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
