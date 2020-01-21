package ru.argerd.repo.newsScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import org.threeten.bp.LocalDate
import ru.argerd.repo.R
import ru.argerd.repo.model.Event

private const val EVENT_EXTRA = "title"
private const val DAYS_EXTRA = "days"

internal class NewsAdapter(internal var events: ArrayList<Event>?)
    : RecyclerView.Adapter<NewsAdapter.Holder>() {
    val picasso: Picasso = Picasso.get()

    init {
        picasso.setIndicatorsEnabled(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        return Holder(inflater.inflate(R.layout.item_of_news, parent, false))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        events?.let {
            holder.bind(it[position])
        }
    }

    override fun getItemCount(): Int {
        return events?.size ?: 0
    }

    internal inner class Holder(itemView: View)
        : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private val photo: ImageView = itemView.findViewById(R.id.photo_of_news)
        private val title: TextView = itemView.findViewById(R.id.title_news)
        private val newsContent: TextView = itemView.findViewById(R.id.news_content)
        private lateinit var event: Event
        private val date: TextView = itemView.findViewById(R.id.date_of_news)
        private lateinit var photoFileName: String

        init {
            photo.setOnClickListener(this)
            title.setOnClickListener(this)
            newsContent.setOnClickListener(this)
        }

        fun bind(event: Event) {
            photoFileName = itemView.context.filesDir.toString() + "/${event.name}/firstPhoto"
            this.event = event
            event.photos?.let { list ->
                list[0]?.let { photo ->
                    picasso.load(photo).into(this.photo)
                }
            }
            this.title.text = event.name
            this.newsContent.text = event.description
            setDate()
        }

        override fun onClick(v: View) {
            val bundle = Bundle()
            bundle.putParcelable(EVENT_EXTRA, event)
            bundle.putString(DAYS_EXTRA, date.text.toString())
            Navigation.findNavController(super.itemView).navigate(R.id.detailScreenActivity, bundle)
        }

        private fun setDate() {
            event.endDate?.let {
                val date = LocalDate.of(
                        it.substringBefore("-").toInt(),
                        it.substringAfterLast("-").toInt(),
                        it.substringBeforeLast("-").substringAfter("-").toInt()
                )
                val currentDate = LocalDate.now()
                if (date.year > currentDate.year) {
                    val diff = (date.toEpochDay() - currentDate.toEpochDay()).toString()
                    var result = if (diff == "1") {
                        itemView.context.resources.getStringArray(R.array.left_variable)[0]
                    } else {
                        itemView.context.resources.getStringArray(R.array.left_variable)[1]
                    }
                    result += " $diff"
                    result += " " + when (diff[diff.length - 1]) {
                        '1' -> itemView.context.resources
                                .getStringArray(R.array.left_days_variable)[0]
                        '2', '3', '4' -> itemView.context.resources
                                .getStringArray(R.array.left_days_variable)[1]
                        else -> itemView.context.resources
                                .getStringArray(R.array.left_days_variable)[2]
                    }
                    val currentDayOfMonth = currentDate.dayOfMonth
                    val currentMonth = when (currentDate.month.toString()) {
                        "JANUARY" -> "1"
                        "FEBRUARY" -> "2"
                        "MARCH" -> "3"
                        "APRIL" -> "4"
                        "MAY" -> "5"
                        "JUNE" -> "6"
                        "JULY" -> "7"
                        "AUGUST" -> "8"
                        "SEPTEMBER" -> "9"
                        "OCTOBER" -> "10"
                        "NOVEMBER" -> "11"
                        "DECEMBER" -> "12"
                        else -> "0"
                    }
                    val dayOfEvent = it.substringBeforeLast("-").substringAfter("-")
                    val monthOfEvent = it.substringAfterLast("-")
                    result += " " + "(" + currentDayOfMonth + "." + currentMonth +
                            " - " + dayOfEvent + "." + monthOfEvent + ")"
                    this.date.text = result
                } else {
                    val monthOfEvent = when (it.substringAfterLast("-")) {
                        "1" -> "Январь"
                        "2" -> "Февраль"
                        "3" -> "Март"
                        "4" -> "Апрель"
                        "5" -> "Май"
                        "6" -> "Июнь"
                        "7" -> "Июль"
                        "8" -> "Август"
                        "9" -> "Сентябрь"
                        "10" -> "Октябрь"
                        "11" -> "Ноябрь"
                        "12" -> "Декабрь"
                        else -> "Январь"
                    }
                    val text = monthOfEvent + " " +
                            it.substringBeforeLast("-").substringAfter("-") +
                            ", " + it.substringBefore("-")
                    this.date.text = text
                }
            }
        }
    }
}
