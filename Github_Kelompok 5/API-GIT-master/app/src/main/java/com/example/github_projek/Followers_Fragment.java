package com.example.github_projek;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.github_projek.adapter.UserAdapter;
import com.example.github_projek.model.api.User;
import com.example.github_projek.viewModel.UserViewModel;

import java.util.ArrayList;

public class Followers_Fragment extends Fragment {
    private String login;

    RecyclerView rvItem;
    UserAdapter userAdapter;
    UserViewModel userViewModel;

    UserAdapter.OnClickListener listener = new UserAdapter.OnClickListener() {
        @Override
        public void onClick(User data) {
            Intent intent = new Intent(getContext(), DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_DATA, data);
            startActivity(intent);
            getActivity().finish();
        }
    };

    public Followers_Fragment(String login) {
        this.login = login;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_followers__fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        userAdapter = new UserAdapter(getContext(), listener);
        userAdapter.notifyDataSetChanged();

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        userViewModel.setGithubApiFollow(this.login, "followers");
        userViewModel.getGithubUserFollow().observe((LifecycleOwner) getContext(), getUserFollow);

        rvItem = view.findViewById(R.id.rv_item);
        rvItem.setLayoutManager(new LinearLayoutManager(getContext()));
        rvItem.setHasFixedSize(true);
        rvItem.setAdapter(userAdapter);


    }

    private Observer<ArrayList<User>> getUserFollow = new Observer<ArrayList<User>>() {
        @Override
        public void onChanged(ArrayList<User> githubUsers) {
            if (githubUsers != null){
                userAdapter.setData(githubUsers);
            }
        }
    };
}