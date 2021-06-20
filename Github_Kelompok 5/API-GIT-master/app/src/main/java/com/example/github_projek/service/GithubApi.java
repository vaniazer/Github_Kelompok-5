package com.example.github_projek.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GithubApi {

    //Membuat koneksi dengan Rest server dan konfigurasi dari link API sebagai rest server
    private Retrofit retrofit;

    private static final String URL_BASE = "https://api.github.com/";

    public GithubInterface getAPI(){
        if (retrofit == null){
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(URL_BASE)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(GithubInterface.class);
    }
}
