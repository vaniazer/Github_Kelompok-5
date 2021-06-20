package com.example.github_projek;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.widget.TextView;

import com.example.github_projek.model.api.User;
import com.example.github_projek.service.GithubResponse;
import com.example.github_projek.viewModel.UserViewModel;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailActivity extends AppCompatActivity {
    public static final String EXTRA_DATA = "extra_parcel";
    private User user;
    private CircleImageView ivAvatar;
    private TextView tvLogin, tvName, tvFollowers, tvFollowing, tvRepositories;

    UserViewModel userViewModel;

    private final String[] TAB_TITLES = new String[]{
            "Followers",
            "Following"
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        user = getIntent().getParcelableExtra(EXTRA_DATA);

        ivAvatar = findViewById(R.id.iv_foto);
        tvName = findViewById(R.id.tvUsername);
        tvLogin = findViewById(R.id.tv_login);
        tvFollowers = findViewById(R.id.tvFollowers);
        tvFollowing = findViewById(R.id.tvFollowing);
        tvRepositories = findViewById(R.id.tvRepository);

        //Pemanggilan view model untuk mengambil detail data user
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        userViewModel.setGithubApiDetail(user.getLogin());
        userViewModel.getGithubDetail().observe(this, getDetailUser);

        // untuk section pager yang digunakan untuk halaman follower dan following di detail activity
        Page_Fragment sectionsPagerAdapter = new Page_Fragment(this, user.getLogin());
        ViewPager2 viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        new TabLayoutMediator(tabs, viewPager,
                (tab, position) -> tab.setText(TAB_TITLES[position])
        ).attach();
        if(getSupportActionBar() != null) {
            getSupportActionBar().setElevation(0);
        }
    }

    private Observer<GithubResponse> getDetailUser = new Observer<GithubResponse>() {
        @Override
        public void onChanged(GithubResponse githubResponse) {
            if (githubResponse != null){
                Picasso.get()
                        .load(githubResponse.getAvatarUrl())
                        .into(ivAvatar);

                tvName.setText(githubResponse.getName());
                tvLogin.setText(githubResponse.getLogin());
                tvFollowers.setText(githubResponse.getFollowers() + "");
                tvFollowing.setText(githubResponse.getFollowing() + "");
                tvRepositories.setText(githubResponse.getPublicRepos() + "");
            }
        }
    };

}