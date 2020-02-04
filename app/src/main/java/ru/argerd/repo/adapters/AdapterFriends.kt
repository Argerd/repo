package ru.argerd.repo.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.argerd.repo.R

class AdapterFriends(
        private val photos: IntArray,
        private val names: Array<String>,
        private val context: Context
) : RecyclerView.Adapter<RecyclerView.ViewHolder?>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.item_friends, parent, false)
        return FriendHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is FriendHolder) {
            holder.bind(names[position], photos[position])
        }
    }

    override fun getItemCount(): Int {
        return photos.size
    }

    private inner class FriendHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val name: TextView = itemView.findViewById(R.id.name_of_friend)
        private val photo: ImageView = itemView.findViewById(R.id.photo_friend)

        fun bind(name: String?, photo: Int) {
            this.name.text = name
            this.photo.setImageResource(photo)
        }
    }
}