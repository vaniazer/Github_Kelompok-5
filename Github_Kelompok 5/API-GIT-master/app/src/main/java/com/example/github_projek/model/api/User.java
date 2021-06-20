package com.example.github_projek.model.api;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "user")
public class User implements Parcelable {
    @PrimaryKey(autoGenerate = true) // auto increment
    @ColumnInfo(name = "id") // nama kolom
    private int id;

    @ColumnInfo(name = "login")
    private String login;

    @ColumnInfo(name = "avatar_url")
    private String avatar_url;

    private String name;
    private String public_repos;
    private String followers;
    private String following;


    public User(String login, String avatar_url, String name, String public_repos, String followers, String following) {
        this.login = login;
        this.avatar_url = avatar_url;
        this.name = name;
        this.public_repos = public_repos;
        this.followers = followers;
        this.following = following;
    }

    protected User(Parcel in) {
        id = in.readInt();
        login = in.readString();
        avatar_url = in.readString();
        name = in.readString();
        public_repos = in.readString();
        followers = in.readString();
        following = in.readString();
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public String getName() {
        return name;
    }

    public String getPublic_repos() {
        return public_repos;
    }

    public String getFollowers() {
        return followers;
    }

    public String getFollowing() {
        return following;
    }



    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(login);
        dest.writeString(avatar_url);
        dest.writeString(name);
        dest.writeString(public_repos);
        dest.writeString(followers);
        dest.writeString(following);
    }
}
