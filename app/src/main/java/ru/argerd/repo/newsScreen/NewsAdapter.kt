package ru.argerd.repo.newsScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import ru.argerd.repo.R
import ru.argerd.repo.detailDescriptionScreen.DetailScreenActivity
import ru.argerd.repo.model.Event

private const val EVENT_EXTRA = "title"

internal class NewsAdapter(private val photo: Int,
                           internal var events: List<Event>)
    : RecyclerView.Adapter<NewsAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        return Holder(inflater.inflate(R.layout.item_of_news, parent, false))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(photo, events[position])
    }

    override fun getItemCount(): Int {
        return events.size
    }

    internal inner class Holder(itemView: View)
        : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private val photo: ImageView = itemView.findViewById(R.id.photo_of_news)
        private val title: TextView = itemView.findViewById(R.id.title_news)
        private val newsContent: TextView = itemView.findViewById(R.id.news_content)
        private lateinit var event: Event

        init {
            photo.setOnClickListener(this)
            title.setOnClickListener(this)
            newsContent.setOnClickListener(this)
        }

        fun bind(photo: Int, event: Event) {
            this.event = event
            this.photo.setImageResource(photo)
            this.title.text = event.title
            this.newsContent.text = event.content
        }

        override fun onClick(v: View) {
            val bundle = Bundle()
            bundle.putParcelable(EVENT_EXTRA, event)
            Navigation.findNavController(super.itemView).navigate(R.id.detailScreenActivity, bundle)
        }
    }
}
