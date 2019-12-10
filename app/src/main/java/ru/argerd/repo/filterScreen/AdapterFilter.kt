package ru.argerd.repo.filterScreen

import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.argerd.repo.R
import ru.argerd.repo.model.Category

internal class AdapterFilter(
        private val categories: ArrayList<Category>,
        private val editor: SharedPreferences.Editor?,
        private val settings: List<String>?
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
        var checked = false
        settings?.let {
            if (it.contains(name)) {
                checked = true
            }
        }
        if (name != null) {
            holder.bind(name, checked)
        } else {
            holder.bind("", checked)
        }
    }

    internal inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameOfCategories: TextView = itemView.findViewById(R.id.categories_name)
        private val switch: Switch = itemView.findViewById(R.id.switch_categories)

        fun bind(nameOfCategories: String, checked: Boolean) {
            switch.isChecked = checked
            this.nameOfCategories.text = nameOfCategories
            switch.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    editor?.putString(nameOfCategories, "")
                } else {
                    editor?.remove(nameOfCategories)
                }
            }
        }
    }
}