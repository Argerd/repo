package ru.argerd.repo.newsScreen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ru.argerd.repo.R;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.Holder> {
    private int count;
    private Context context;
    private int[] photos;
    private String[] titles;
    private String[] newsContents;

    NewsAdapter(int count, Context context, int[] photos,
                String[] titles, String[] newsContents) {
        this.count = count;
        this.context = context;
        this.photos = photos;
        this.titles = titles;
        this.newsContents = newsContents;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return new Holder(inflater.inflate(R.layout.item_of_news, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.bind(photos[position], titles[position], newsContents[position]);
    }

    @Override
    public int getItemCount() {
        return count;
    }

    class Holder extends RecyclerView.ViewHolder {
        private ImageView photo;
        private TextView title;
        private TextView newsContent;

        Holder(@NonNull View itemView) {
            super(itemView);
            photo = itemView.findViewById(R.id.photo_of_news);
            title = itemView.findViewById(R.id.title_news);
            newsContent = itemView.findViewById(R.id.news_content);
        }

        void bind(int photo, String title, String newsContent) {
            this.photo.setImageResource(photo);
            this.title.setText(title);
            this.newsContent.setText(newsContent);
        }
    }
}
