package ru.argerd.repo.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.argerd.repo.R

class PeopleAdapter(
        private val context: Context,
        private val photos: IntArray,
        private val count: Int
) : RecyclerView.Adapter<RecyclerView.ViewHolder?>() {

    companion object {
        private val TAG = PeopleAdapter::class.java.name
        private const val DEFAULT = 0
        private const val COUNTER = 1
    }

    override fun getItemViewType(position: Int): Int {
        Log.d(TAG, "position: $position")
        return if (position == photos.size) COUNTER else DEFAULT
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == COUNTER) Counter(LayoutInflater.from(context).inflate(
                R.layout.counter_item, parent, false
        )) else Avatar(LayoutInflater.from(context).inflate(
                R.layout.item_people_in_detail, parent, false
        ))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is Counter) holder.bind(count)
        else if (holder is Avatar) holder.bind(photos[position])
    }

    override fun getItemCount(): Int {
        return photos.size + 1
    }

    private inner class Avatar internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val avatar: ImageView = itemView.findViewById(R.id.avatar)

        fun bind(photo: Int) {
            avatar.setImageResource(photo)
        }
    }

    private inner class Counter internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val counter: TextView = itemView.findViewById(R.id.counter_text)

        fun bind(count: Int) {
            counter.text = count.toString()
        }
    }
}