package ru.argerd.repo.detailDescriptionScreen

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
        event?.let {
            detailsToolbarTitle.text = it.title
            titleEvent.text = it.title
            subtitleEvent.text = it.nameOfOrganization
            address.text = it.address
            phonesNumbers.text = it.phones
            eventContent.text = it.content
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