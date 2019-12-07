package ru.argerd.repo.filterScreen

import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.argerd.repo.model.Category
import ru.argerd.repo.R

class AdapterFilter(
        private val categories: List<Category>,
        val editor: SharedPreferences.Editor
) : RecyclerView.Adapter<AdapterFilter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_filter, parent, false))
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val name = categories[position].name
        if (name != null) {
            holder.bind(name)
        } else {
            holder.bind("")
        }
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameOfCategories: TextView = itemView.findViewById(R.id.categories_name)
        private val switch: Switch = itemView.findViewById(R.id.switch_categories)

        fun bind(nameOfCategories: String) {
            this.nameOfCategories.text = nameOfCategories
            switch.setOnCheckedChangeListener { _, isChecked ->
                editor.putBoolean(nameOfCategories, isChecked)
            }
        }
    }
}