package ru.argerd.repo.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.argerd.repo.R
import java.util.*

class ListAdapter(
        var items: List<String?>,
        private val context: Context)
    : RecyclerView.Adapter<ListAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(context)
        return Holder(inflater.inflate(R.layout.item_list_results, parent, false))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameOfItem: TextView = itemView.findViewById(R.id.name_of_item)

        fun bind(nameOfItem: String?) {
            this.nameOfItem.text = nameOfItem
        }

    }


}