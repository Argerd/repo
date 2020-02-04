package ru.argerd.repo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.argerd.repo.R
import ru.argerd.repo.data.model.Category

class AdapterFilter(private val onSwitchCategory: (isChecked: Boolean,
                                                   nameOfCategories: String) -> Unit)
    : RecyclerView.Adapter<AdapterFilter.Holder>() {

    var categories: List<Category>? = null
    var settings: List<String>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_filter, parent, false))
    }

    override fun getItemCount(): Int {
        return categories?.size ?: 0
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        categories?.let { it ->
            val name = it[position].name
            var checked = false
            settings?.let { settings ->
                if (settings.contains(name)) {
                    checked = true
                }
            }
            if (name != null) {
                holder.bind(name, checked)
            } else {
                holder.bind("", checked)
            }
        }
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameOfCategories: TextView = itemView.findViewById(R.id.categories_name)
        private val switch: Switch = itemView.findViewById(R.id.switch_categories)

        fun bind(nameOfCategories: String, checked: Boolean) {
            switch.isChecked = checked
            this.nameOfCategories.text = nameOfCategories
            switch.setOnCheckedChangeListener { _, isChecked ->
                onSwitchCategory(isChecked, nameOfCategories)
            }
        }
    }
}