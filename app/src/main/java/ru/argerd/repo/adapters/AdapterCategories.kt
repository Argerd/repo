package ru.argerd.repo.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.argerd.repo.R
import ru.argerd.repo.data.model.Category

class AdapterCategories(
        private val context: Context
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val picasso = Picasso.get()
    lateinit var listOfCategories: List<Category>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.item_help, parent, false)
        return CategoriesHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CategoriesHolder) {
            holder.bind(listOfCategories[position].image,
                    listOfCategories[position].name)
        }
    }

    override fun getItemCount(): Int {
        return listOfCategories.size
    }

    private inner class CategoriesHolder(itemView: View)
        : RecyclerView.ViewHolder(itemView) {
        private val image: ImageView = itemView.findViewById(R.id.help_category_image)
        private val nameCategory: TextView = itemView.findViewById(R.id.help_category_text)

        fun bind(image: String?, nameCategory: String?) {
            picasso.load(image).into(this.image)
            this.nameCategory.text = nameCategory
        }

    }

}