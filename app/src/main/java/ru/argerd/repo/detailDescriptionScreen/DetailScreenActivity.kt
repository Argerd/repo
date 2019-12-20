package ru.argerd.repo.detailDescriptionScreen

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.argerd.repo.R
import ru.argerd.repo.model.Event

private const val EVENT_EXTRA = "title"
private const val DAYS_EXTRA = "days"

class DetailScreenActivity : AppCompatActivity() {
    private var event: Event? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_screen)

        val toolbar = findViewById<Toolbar>(R.id.details_toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_back)
        toolbar.setNavigationOnClickListener { onBackPressed() }

        event = intent.getParcelableExtra(EVENT_EXTRA)
        val detailsToolbarTitle: TextView = findViewById(R.id.details_toolbar_title)
        val titleEvent: TextView = findViewById(R.id.title_event)
        val subtitleEvent: TextView = findViewById(R.id.subtitle_event)
        val address: TextView = findViewById(R.id.address)
        val phonesNumbers: TextView = findViewById(R.id.phones_numbers)
        val eventContent: TextView = findViewById(R.id.event_content)

        val picasso = Picasso.get()
        val firstPhoto: ImageView = findViewById(R.id.firstPhoto)
        val secondPhoto: ImageView = findViewById(R.id.secondPhoto)
        val thirdPhoto: ImageView = findViewById(R.id.thirdPhoto)

        event?.let {
            detailsToolbarTitle.text = it.title
            titleEvent.text = it.title
            subtitleEvent.text = it.nameOfOrganization
            address.text = it.address
            it.phones?.let { phones ->
                var text = ""
                for (i in phones.indices) {
                    text += if (i != phones.size - 1) {
                        phones[i] + "\n"
                    } else {
                        phones[i]
                    }
                }
                phonesNumbers.text = text
            }
            eventContent.text = it.content
            it.photos?.let { photos ->
                when (photos.size) {
                    1 -> picasso.load(photos[0]).into(firstPhoto)
                    2 -> {
                        picasso.load(photos[0]).into(firstPhoto)
                        picasso.load(photos[1]).into(secondPhoto)
                    }
                    3 -> {
                        picasso.load(photos[0]).into(firstPhoto)
                        picasso.load(photos[1]).into(secondPhoto)
                        picasso.load(photos[2]).into(thirdPhoto)
                    }
                }
            }
        }
        val leftDays: TextView = findViewById(R.id.left_days)
        leftDays.text = intent.getStringExtra(DAYS_EXTRA)

        val photos = intArrayOf(R.drawable.photo_1, R.drawable.photo_2, R.drawable.photo_3,
                R.drawable.photo_4, R.drawable.photo_5)

        val listOfFriends = findViewById<RecyclerView>(R.id.list_of_avatars)

        listOfFriends.layoutManager = LinearLayoutManager(this,
                RecyclerView.HORIZONTAL, false)
        listOfFriends.adapter = PeopleAdapter(this, photos, 103)
        val resources = resources
        listOfFriends.addItemDecoration(Decorator(
                resources.getDimension(R.dimen.margin_first),
                resources.getDimension(R.dimen.margin_top_bottom),
                resources.getDimension(R.dimen.margin_left)))
    }
}