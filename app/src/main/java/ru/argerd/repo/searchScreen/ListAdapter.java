package ru.argerd.repo.searchScreen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ru.argerd.repo.R;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.Holder> {
    private String[] namesOfItems;
    private Context context;

    ListAdapter(String[] namesOfItems, Context context) {
        this.namesOfItems = namesOfItems;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return new Holder(inflater.inflate(R.layout.item_list_results, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.bind(namesOfItems[position]);
    }

    @Override
    public int getItemCount() {
        return namesOfItems.length;
    }

    class Holder extends RecyclerView.ViewHolder {
        private TextView nameOfItem;

        Holder(@NonNull View itemView) {
            super(itemView);
            nameOfItem = itemView.findViewById(R.id.name_of_item);
        }

        void bind(String nameOfItem) {
            this.nameOfItem.setText(nameOfItem);
        }
    }
}
