package com.example.github_projek.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.github_projek.DetailActivity;
import com.example.github_projek.HomeFragment;
import com.example.github_projek.R;
import com.example.github_projek.model.api.User;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    ArrayList<User> Users = new ArrayList<>();
    Context context;
    OnClickListener mCallback;

    //Pemanggilan interface method untuk digunakan di HomeFragment
    public UserAdapter(Context context, OnClickListener mCallback) {
        this.context = context;
        this.mCallback = mCallback;
    }

    //Mengeset Data dengan reload data di list
    public void setData(ArrayList<User> items){
        Users.clear();
        Users.addAll(items);
        notifyDataSetChanged();
        //me-redraw Recycle View untuk bisa menampilkan data baru
    }

    @NonNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder holder, int position) {
        holder.bind(Users.get(position));
    }

    @Override
    public int getItemCount() {
        return Users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView Foto;
        TextView Username;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            Foto = itemView.findViewById(R.id.iv_avatar);
            Username = itemView.findViewById(R.id.tv_nama);
        }

        public void bind(User user) {
            //mengeset data username dan foto
            Username.setText(user.getLogin());
            Picasso.get()
                    .load(user.getAvatar_url())
                    .into(Foto);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCallback.onClick(user);
                }
            });
        }

    }

    public interface OnClickListener {
        void onClick(User data);
    }



}
