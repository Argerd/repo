package ru.argerd.repo.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ru.argerd.repo.R;

public class PeopleAdapter extends RecyclerView.Adapter {
    private static final String TAG = PeopleAdapter.class.getName();

    private static final int DEFAULT = 0;
    private static final int COUNTER = 1;

    private Context context;
    private int[] photos;
    private int count;

    public PeopleAdapter(Context context, int[] photos, int count) {
        this.context = context;
        this.photos = photos;
        this.count = count;
    }

    @Override
    public int getItemViewType(int position) {
        Log.d(TAG, "position: " + position);
        return position == photos.length ? COUNTER : DEFAULT;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return viewType == COUNTER ?
                new Counter(LayoutInflater.from(context).inflate(
                        R.layout.counter_item, parent, false
                )) :
                new Avatar(LayoutInflater.from(context).inflate(
                        R.layout.item_people_in_detail, parent, false
                ));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof Counter) ((Counter) holder).bind(count);
        else if (holder instanceof Avatar) ((Avatar) holder).bind(photos[position]);
    }

    @Override
    public int getItemCount() {
        return photos.length + 1;
    }

    private class Avatar extends RecyclerView.ViewHolder {
        private ImageView avatar;

        Avatar(@NonNull View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.avatar);
        }

        void bind(int photo) {
            avatar.setImageResource(photo);
        }
    }

    private class Counter extends RecyclerView.ViewHolder {
        private TextView counter;

        Counter(@NonNull View itemView) {
            super(itemView);
            counter = itemView.findViewById(R.id.counter_text);
        }

        void bind(int count) {
            counter.setText("+" + count);
        }
    }
}
