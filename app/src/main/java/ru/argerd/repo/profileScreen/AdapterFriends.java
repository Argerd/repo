package ru.argerd.repo.profileScreen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ru.argerd.repo.R;

public class AdapterFriends extends RecyclerView.Adapter {
    private Context context;
    private int[] photos;
    private String[] names;

    AdapterFriends(int[] photos, String[] names, Context context) {
        this.photos = photos;
        this.names = names;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_recycler_friends, parent, false);
        return new FriendHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof FriendHolder) {
            ((FriendHolder) holder).bind(names[position], photos[position]);
        }
    }

    @Override
    public int getItemCount() {
        return photos.length;
    }

    private class FriendHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private ImageView photo;

        public FriendHolder(@NonNull View itemView) {
            super(itemView);
            this.name = itemView.findViewById(R.id.name_of_friend);
            this.photo = itemView.findViewById(R.id.photo_friend);
        }

        public void bind(String name, int photo) {
            this.name.setText(name);
            this.photo.setImageResource(photo);
        }
    }
}
