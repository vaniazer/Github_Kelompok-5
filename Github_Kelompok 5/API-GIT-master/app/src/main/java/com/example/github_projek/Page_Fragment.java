package com.example.github_projek;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class Page_Fragment extends FragmentStateAdapter {
    private String login;

    public Page_Fragment(@NonNull FragmentActivity fragmentActivity, String login) {
        super(fragmentActivity);
        this.login = login;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new Followers_Fragment(login);
                break;
            case 1:
                fragment = new Following_Fragment(login);
                break;
        }
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
