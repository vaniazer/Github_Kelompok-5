package com.example.github_projek;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.github_projek.adapter.UserAdapter;
import com.example.github_projek.model.api.User;
import com.example.github_projek.viewModel.UserViewModel;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    private RecyclerView rvItem;
    private UserAdapter userAdapter;
    UserViewModel userViewModel;

    //dari adapter untuk langsung intent ke halaman detail activity
    UserAdapter.OnClickListener listener = new UserAdapter.OnClickListener() {
        @Override
        public void onClick(User data) {
            Intent intent = new Intent(getContext(), DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_DATA, data);
            startActivity(intent);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Pemanggilan adapter untuk mengambil data
        userAdapter = new UserAdapter(getContext(),listener);
        userAdapter.notifyDataSetChanged();

        //Pemanggilan view model untuk mengambil data user github API
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        userViewModel.setGithubAPI();
        userViewModel.getGithubUser().observe(getViewLifecycleOwner(), getUsers);

        rvItem = view.findViewById(R.id.rv_item);
        rvItem.setLayoutManager(new LinearLayoutManager(getContext()));
        rvItem.setHasFixedSize(true);
        rvItem.setAdapter(userAdapter);

    }

    private Observer<ArrayList<User>> getUsers = new Observer<ArrayList<User>>() {
        @Override
        public void onChanged(ArrayList<User> githubUsers) {
            if (githubUsers != null){
                Log.d("getUser", githubUsers.toString());
                userAdapter.setData(githubUsers);
            }
        }
    };


}